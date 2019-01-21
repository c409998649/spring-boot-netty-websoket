package com.chen.route.route.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 发送信息类
 *
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-17 下午5:28
 **/
@Data
public class PersonToMessage implements Serializable {

    // 发送人账号
    private String sendUserId;
    // 接收人账号
    private String receiveUserId;
    // 消息
    private String msg;
}
