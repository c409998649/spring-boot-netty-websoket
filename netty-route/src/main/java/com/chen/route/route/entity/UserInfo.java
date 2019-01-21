package com.chen.route.route.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author chenzhiying@zbj.com
 * @title 用户信息
 * @date 2019-01-17 下午5:03
 **/
@Data
public class UserInfo implements Serializable {

    // 用户编码
    private int userId;
    // 用户账号
    private String account;
    // 用户姓名
    private String userName;
}
