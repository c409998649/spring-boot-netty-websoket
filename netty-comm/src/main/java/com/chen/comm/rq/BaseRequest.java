package com.chen.comm.rq;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 陈智颖
 * @date 2018-12-22 上午11:51
 **/
@Data
public class BaseRequest {

    @ApiModelProperty(required=false, value="唯一请求号", example = "1234567890")
    private String reqNo;

    @ApiModelProperty(required=false, value="当前请求的时间戳", example = "0")
    private int timeStamp;

    public BaseRequest() {
        this.setTimeStamp((int)(System.currentTimeMillis() / 1000));
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "reqNo='" + reqNo + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
