package com.chen.route.route.entity;

import lombok.Data;

/**
 * 发送消息类
 *
 * @author chenzhiying@zbj.com
 * @title 发送消息类
 * @date 2019-01-17 下午4:46
 **/
@Data
public class ChatReqVO {

    // 接受者编码
    private Integer userId;
    // 接受者信息
    private String msg;
}
