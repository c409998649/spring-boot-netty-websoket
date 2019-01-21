package com.chen.route.route.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-17 下午5:20
 **/
@Data
public class ServerResVO implements Serializable {

    // ip地址
    private String ip;
    // 服务端端口
    private Integer serverPort;
    // http端口
    private Integer httpPort;
}
