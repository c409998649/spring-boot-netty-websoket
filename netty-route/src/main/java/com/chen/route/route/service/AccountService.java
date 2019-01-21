package com.chen.route.route.service;

import com.chen.route.route.entity.ChatReqVO;
import com.chen.route.route.entity.ServerResVO;

/**
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-17 下午4:26
 **/
public interface AccountService {

    /**
     * 推送消息
     * @param url url
     * @param userId 发送者的用户编码
     * @param groupReqVO 消息
     * @throws Exception
     */
    void pushMsg(String url, Integer userId, ChatReqVO groupReqVO) throws Exception;

    /**
     * 获取某个用户的路有关系
     * @param userId 用户编码
     * @return 获取某个用户的路有关系
     */
    ServerResVO loadRouteRelatedByUserId(Integer userId) ;
}
