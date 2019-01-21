package com.chen.server.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class HeartbeatInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                //五秒没有收到消息 将IdleStateHandler 添加到 ChannelPipeline 中
                .addLast(new IdleStateHandler(5, 0, 0))

                // HttpServerCodec：将请求和应答消息解码为HTTP消息
                .addLast("http-codec",new HttpServerCodec())
                // HttpObjectAggregator：将HTTP消息的多个部分合成一条完整的HTTP消息
                .addLast("aggregator",new HttpObjectAggregator(65536))
                // ChunkedWriteHandler：向客户端发送HTML5文件
                .addLast("http-chunked",new ChunkedWriteHandler())

                .addLast(new HeartBeatSimpleHandle());
    }
}
