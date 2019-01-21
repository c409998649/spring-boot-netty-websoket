package com.chen.client;

import com.chen.comm.protocol.BaseRequestProto;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 陈智颖
 * @date 2018-12-22 上午11:36
 **/
@Slf4j
public class ProtocolUtil {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        BaseRequestProto.RequestProtocol protocol = BaseRequestProto.RequestProtocol.newBuilder()
                .setRequestId(123)
                .setReqMsg("你好啊")
                .build();
        byte[] encode = encode(protocol);
        BaseRequestProto.RequestProtocol parseFrom = decode(encode);
        System.out.println(protocol.toString());
        System.out.println(protocol.toString().equals(parseFrom.toString()));
    }

    /**
     * 编码
     * @param protocol
     * @return
     */
    public static byte[] encode(BaseRequestProto.RequestProtocol protocol){
        return protocol.toByteArray() ;
    }
    /**
     * 解码
     * @param bytes
     * @return
     * @throws InvalidProtocolBufferException
     */
    public static BaseRequestProto.RequestProtocol decode(byte[] bytes) throws InvalidProtocolBufferException {
        return BaseRequestProto.RequestProtocol.parseFrom(bytes);
    }
}
