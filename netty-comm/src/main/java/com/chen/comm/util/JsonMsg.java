package com.chen.comm.util;

import lombok.Data;

/**
 * @author 陈智颖
 * @create 2018-06-21 上午11:01
 **/
@Data
public class JsonMsg {

    // 返回值 200返回成功 401返回异常 404系统异常
    private int code = 200;
    // 返回页面输出
    private String msg;
    // 返回值
    public Object data;
    // 查询总条数
    public long total;

    public JsonMsg(){

    }

    public JsonMsg(String msg){
        this.msg = msg;
    }

    public JsonMsg(String msg, Object data){
        this.msg = msg;
        this.data = data;
    }

    public JsonMsg(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
