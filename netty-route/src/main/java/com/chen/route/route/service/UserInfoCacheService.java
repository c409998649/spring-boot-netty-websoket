package com.chen.route.route.service;

import com.chen.route.route.entity.UserInfo;

/**
 * 用户信息保存本地map中
 *
 * @author chenzhiying@zbj.com
 * @title 保存本地map中
 * @date 2019-01-17 下午5:02
 **/
public interface UserInfoCacheService {

    /**
     * 根据用户账号查询用户数据
     *
     * @param userId 用户账号
     * @return com.chen.route.route.entity.UserInfo
     * @title 根据用户账号查询用户数据
     * @author chenzhiying@zbj.com
     * @date 19-1-17
     * @since openJDK 1.8
     **/
    UserInfo loadUserInfoByUserId(Integer userId);

    /**
     * 保存用户信息
     *
     * @param userInfo 用户类
     * @return void
     * @title 保存用户信息
     * @author chenzhiying@zbj.com
     * @date 19-1-17
     * @since openJDK 1.8
     **/
    void saveUserInfo(UserInfo userInfo);
}
