package com.chen.route.route.controller;

import com.chen.comm.util.JsonMsg;
import com.chen.route.route.cache.RedisService;
import com.chen.route.route.cache.ServerCache;
import com.chen.route.route.constant.Constant;
import com.chen.route.route.entity.ChatReqVO;
import com.chen.route.route.entity.PersonToMessage;
import com.chen.route.route.entity.ServerResVO;
import com.chen.route.route.entity.UserInfo;
import com.chen.route.route.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * controller处理类
 * @author chenzhiying@zbj.com
 * @title controller处理类
 * @date 2019-01-17 下午3:11
 **/
@Controller
public class RouteController {

    @Resource
    private ServerCache serverCache;

    @Resource
    private RedisService redisService;

    @Resource
    private AccountService accountService;

    /**
     * 用户登录
     *
     * @param userInfo 参数
     * @return com.chen.comm.util.JsonMsg
     * @title 用户登录
     * @author chenzhiying@zbj.com
     * @date 19-1-17
     * @since openJDK 1.8
     **/
    @PostMapping("/login")
    @ResponseBody
    public JsonMsg login(@RequestBody UserInfo userInfo){
        JsonMsg jsonMsg = new JsonMsg();
        String server = serverCache.selectServer();
        redisService.set(Constant.ROUTE+userInfo.getUserId(), server);
        redisService.set(Constant.ACCTOUNT+userInfo.getUserId(), userInfo.getAccount());
        redisService.set(Constant.USER+userInfo.getUserId(), userInfo.getUserName());
        return jsonMsg;
    }

    /**
     * 私聊
     *
     * @param personToMessage 消息
     * @return com.chen.comm.util.JsonMsg
     * @title 私聊
     * @author chenzhiying@zbj.com
     * @date 19-1-17
     * @since openJDK 1.8
     **/
    @PostMapping("/personToMessage")
    @ResponseBody
    public JsonMsg personToMessage(@RequestBody PersonToMessage personToMessage) throws Exception {
        JsonMsg jsonMsg = new JsonMsg();
        ServerResVO serverResVO = accountService.loadRouteRelatedByUserId(personToMessage.getReceiveUserId());
        String http = "http://"+serverResVO.getIp()+":"+serverResVO.getHttpPort()+"/sendMsg";
        ChatReqVO chatReqVO = new ChatReqVO();
        chatReqVO.setUserId(personToMessage.getReceiveUserId());
        chatReqVO.setMsg(personToMessage.getMsg());
        accountService.pushMsg(http, personToMessage.getSendUserId(), chatReqVO);
        return jsonMsg;
    }
}
