package com.chen.server.netty;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 心跳连接通道
 * @author 陈智颖
 * @date 2018-12-21 下午9:47
 **/
public class NettySocketHolder {

    // 装载连接桶
    private static final Map<Long, NioSocketChannel> MAP = new ConcurrentHashMap<>(16);

    /**
     * 装载桶
     * @param key 唯一标识
     * @param channel 连接
     */
    public static void put(Long key, NioSocketChannel channel){
        MAP.put(key, channel);
    }

    /**
     * 根据唯一表示取出连接
     * @param key
     * @return
     */
    public static NioSocketChannel get(Long key){
        return MAP.get(key);
    }

    public static Map<Long, NioSocketChannel> getMAP() {
        return MAP;
    }

    /**
     * 删除连接
     * @param nioSocketChannel
     */
    public static void remove(NioSocketChannel nioSocketChannel) {
        MAP.entrySet().stream().filter(entry -> entry.getValue() == nioSocketChannel).forEach(entry -> MAP.remove(entry.getKey()));
    }
}
