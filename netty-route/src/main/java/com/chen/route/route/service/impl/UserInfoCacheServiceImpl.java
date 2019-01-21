package com.chen.route.route.service.impl;

import com.chen.route.route.cache.RedisService;
import com.chen.route.route.constant.Constant;
import com.chen.route.route.entity.UserInfo;
import com.chen.route.route.service.UserInfoCacheService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-17 下午5:04
 **/
@Service
public class UserInfoCacheServiceImpl implements UserInfoCacheService {

    @Resource
    private RedisService redisService;

    private ConcurrentMap<Integer, UserInfo> USER_MAP = new ConcurrentHashMap<>();

    @Override
    public UserInfo loadUserInfoByUserId(Integer userId) {
        UserInfo userInfo = USER_MAP.get(userId);
        if(userInfo != null){
            return userInfo;
        }
        String account = redisService.get(Constant.ACCOUNT + userId).toString();
        String userName = redisService.get(Constant.USER + userId).toString();
        if(userName != null){
            userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setAccount(account);
            userInfo.setUserName(userName);
            USER_MAP.put(userId, userInfo);
        }
        return userInfo;
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        USER_MAP.put(userInfo.getUserId(), userInfo);
    }
}
