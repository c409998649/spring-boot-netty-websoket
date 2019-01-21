package com.chen.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.chen.client.entity.UserInfo;
import com.chen.client.entity.UserMap;
import com.chen.comm.util.JsonMsg;
import com.chen.comm.util.OkHttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-18 上午10:05
 **/
@Controller
public class IndexController {

    @Value("${login.url}")
    private String loginUrl;

    @Value("${send.url}")
    private String sendUrl;

    private int userId = 0;

    @GetMapping("/index")
    public String index(){
        return "/index";
    }

    @GetMapping("/login")
    @ResponseBody
    public JsonMsg login(String account, String userName) throws IOException {
        JsonMsg jsonMsg = new JsonMsg();
        userId = userId + 1;
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setAccount(account);
        userInfo.setUserName(userName);
        UserMap.put(account, userInfo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("account", account);
        jsonObject.put("userName", userName);
        OkHttpUtil.postJsonParams(loginUrl, jsonObject.toString());
        return jsonMsg;
    }

    /**
     * 发送消息
     *
     * @param receiveUserId 接收用户编码
     * @param sendUserId 发送者用户编码
     * @param msg 发送消息
     * @return com.chen.comm.util.JsonMsg
     * @title <一句话说明功能>
     * @author chenzhiying@zbj.com
     * @date 19-1-18
     * @since openJDK 1.8
     **/
    @GetMapping("/send")
    @ResponseBody
    public JsonMsg send(String receiveUserId, String sendUserId, String msg) throws IOException {
        JsonMsg jsonMsg = new JsonMsg();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("receiveUserId", receiveUserId);
        jsonObject.put("sendUserId", sendUserId);
        jsonObject.put("msg", msg);
        OkHttpUtil.postJsonParams(sendUrl, jsonObject.toString());
        return jsonMsg;
    }
}
