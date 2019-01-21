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

    private ConcurrentMap<String, UserInfo> USER_MAP = new ConcurrentHashMap<>();

    @Override
    public UserInfo loadUserInfoByUserId(String account) {
        UserInfo userInfo = USER_MAP.get(account);
        if(userInfo != null){
            return userInfo;
        }
        String userName = redisService.get(Constant.ACCTOUNT + account).toString();
        if(userName != null){
            userInfo = new UserInfo();
            userInfo.setAccount(account);
            userInfo.setUserName(userName);
            USER_MAP.put(account, userInfo);
        }
        return userInfo;
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        USER_MAP.put(userInfo.getAccount(), userInfo);
    }
}
