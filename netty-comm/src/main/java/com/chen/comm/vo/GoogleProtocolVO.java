package com.chen.comm.vo;

import com.chen.comm.rq.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 陈智颖
 * @date 2018-12-22 上午11:48
 **/
@Data
public class GoogleProtocolVO extends BaseRequest {
    @NotNull(message = "requestId 不能为空")
    @ApiModelProperty(required = true, value = "requestId", example = "123")
    private Integer requestId ;

    @NotNull(message = "msg 不能为空")
    @ApiModelProperty(required = true, value = "msg", example = "hello")
    private String msg ;

    @Override
    public String toString() {
        return "GoogleProtocolVO{" +
                "requestId=" + requestId +
                ", msg='" + msg + '\'' +
                "} " + super.toString();
    }
}
