package com.chen.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.chen.client.entity.UserInfo;
import com.chen.client.entity.UserMap;
import com.chen.comm.util.JsonMsg;
import com.chen.comm.util.OkHttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 发送者
     *
     * @return java.lang.String
     * @title <一句话说明功能>
     * @author chenzhiying@zbj.com
     * @date 19-1-21
     * @since openJDK 1.8
     **/
    @GetMapping("/send")
    public String index(){
        return "/send";
    }

    /**
     * 接收者
     *
     * @return java.lang.String
     * @title <一句话说明功能>
     * @author chenzhiying@zbj.com
     * @date 19-1-21
     * @since openJDK 1.8
     **/
    @GetMapping("/receive")
    public String receive(){
        return "/receive";
    }

    @PostMapping("/login")
    @ResponseBody
    public JsonMsg login(Integer userId, String account, String userName) {
        JsonMsg jsonMsg = new JsonMsg();
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
        jsonMsg.setData(userInfo);
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
    @PostMapping("/send")
    @ResponseBody
    public JsonMsg send(String receiveUserId, String sendUserId, String msg) {
        JsonMsg jsonMsg = new JsonMsg();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("receiveUserId", receiveUserId);
        jsonObject.put("sendUserId", sendUserId);
        jsonObject.put("msg", msg);
        OkHttpUtil.postJsonParams(sendUrl, jsonObject.toString());
        return jsonMsg;
    }
}
