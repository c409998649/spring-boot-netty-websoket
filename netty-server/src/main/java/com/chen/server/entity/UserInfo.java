package com.chen.server.entity;

import lombok.Data;

/**
 * 接收消息
 *
 * @author chenzhiying@zbj.com
 * @title 接收消息
 * @date 2019-01-18 下午1:55
 **/
@Data
public class UserInfo {

    // 用户编码
    private int userId;
    // 账号
    private String account;
    // 消息
    private String msg;
}
