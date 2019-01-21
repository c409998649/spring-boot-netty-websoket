package com.chen.client.entity;

import lombok.Data;

/**
 * 用户信息存储
 *
 * @author chenzhiying@zbj.com
 * @title 用户
 * @date 2019-01-18 下午3:34
 **/
@Data
public class UserInfo {

    // 用户编码
    private int userId;
    // 用户账号
    private String account;
    // 用户姓名
    private String userName;
}
