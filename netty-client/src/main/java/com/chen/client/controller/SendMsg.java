package com.chen.client.controller;

import com.chen.client.netty.HeartbeatClient;
import com.chen.comm.vo.GoogleProtocolVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 陈智颖
 * @date 2018-12-22 上午11:45
 **/
@Controller
public class SendMsg {

    @Resource
    private HeartbeatClient heartbeatClient;

    @RequestMapping("/sendMsg")
    @ResponseBody
    public String sendMsg(){
        for (int i = 0; i < 100; i++) {
            GoogleProtocolVO googleProtocolVO = new GoogleProtocolVO();
            googleProtocolVO.setRequestId(i);
            googleProtocolVO.setMsg("发送消息"+i);
            heartbeatClient.sendGoogleProtocolMsg(googleProtocolVO);
        }
        return "发送完成";
    }
}
