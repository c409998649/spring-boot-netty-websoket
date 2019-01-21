package com.chen.route.route.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chen.comm.util.OkHttpUtil;
import com.chen.route.route.cache.RedisService;
import com.chen.route.route.constant.Constant;
import com.chen.route.route.entity.ChatReqVO;
import com.chen.route.route.entity.ServerResVO;
import com.chen.route.route.entity.UserInfo;
import com.chen.route.route.service.AccountService;
import com.chen.route.route.service.UserInfoCacheService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-17 下午4:26
 **/
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private RedisService redisService;

    @Resource
    private UserInfoCacheService userInfoCacheService;

    @Override
    public void pushMsg(String url, Integer userId, ChatReqVO groupReqVO) throws Exception {
        UserInfo userInfo = userInfoCacheService.loadUserInfoByUserId(userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", userInfo.getUserName() + ":【" + groupReqVO.getMsg() + "】");
        jsonObject.put("userId", groupReqVO.getUserId());
        OkHttpUtil.postJsonParams(url, jsonObject.toJSONString());
    }

    @Override
    public ServerResVO loadRouteRelatedByUserId(Integer userId) {
        String service = redisService.get(Constant.ROUTE + userId).toString();
        ServerResVO serverResVO = new ServerResVO();
        String[] split = service.split(":");
        serverResVO.setIp(split[0]);
        serverResVO.setServerPort(Integer.valueOf(split[1]));
        serverResVO.setHttpPort(Integer.valueOf(split[2]));
        return serverResVO;
    }
}
