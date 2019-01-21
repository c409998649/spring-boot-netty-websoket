package com.chen.server.netty;

import com.alibaba.fastjson.JSONObject;
import com.chen.server.entity.UserInfo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * netty 启动程序
 * @author 陈智颖
 * @date 2018-12-21 下午10:50
 **/
@Component
@Slf4j
public class HeartBeatServer {

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();
    @Value("${netty.server.port}")
    private int nettyPort;
    /**
     * 启动 Netty
     *
     * @return
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(nettyPort))
                //保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new HeartbeatInitializer());
        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            log.info("启动 Netty 成功");
        }
    }
    /**
     * 销毁
     */
    @PreDestroy
    public void destroy() {
        boss.shutdownGracefully().syncUninterruptibly();
        work.shutdownGracefully().syncUninterruptibly();
        log.info("关闭 Netty 成功");
    }

    /**
     * 发送 Google Protocol 编码消息
     * @param userInfo 消息
     */
    public void sendMsg(final UserInfo userInfo){
        NioSocketChannel socketChannel = NettySocketHolder.get(Long.valueOf(userInfo.getUserId()));
        ChannelFuture future = socketChannel.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(userInfo)));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                log.info("服务端手动发送 Google Protocol 成功={}", userInfo.getMsg());
            }
        });
    }

}

