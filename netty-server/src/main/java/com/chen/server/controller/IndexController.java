package com.chen.server.controller;

import com.chen.comm.util.JsonMsg;
import com.chen.comm.vo.GoogleProtocolVO;
import com.chen.server.entity.UserInfo;
import com.chen.server.netty.HeartBeatServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 服务端controller
 *
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-17 下午5:35
 **/
@Controller
public class IndexController {


    @Resource
    private HeartBeatServer heartBeatServer;

    /**
     * 发送消息
     *
     * @param userInfo 参数
     * @return com.chen.comm.util.JsonMsg
     * @title <一句话说明功能>
     * @author chenzhiying@zbj.com
     * @date 19-1-17
     * @since openJDK 1.8
     **/
    @PostMapping("/sendMsg")
    @ResponseBody
    public JsonMsg sendMsg(@RequestBody UserInfo userInfo){
        JsonMsg jsonMsg = new JsonMsg();
        GoogleProtocolVO googleProtocolVO = new GoogleProtocolVO();
        googleProtocolVO.setRequestId(userInfo.getUserId());
        googleProtocolVO.setMsg(userInfo.getMsg());
        heartBeatServer.sendMsg(googleProtocolVO);
        return jsonMsg;
    }
}
