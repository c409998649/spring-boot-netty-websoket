package com.chen.route.route.Zookeeper;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 读取Zookeeper配置文件信息
 *
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-17 上午11:04
 **/
@Component
@Data
public class AppConfiguration implements Serializable {

    @Value("${app.application.name}")
    private String zkRoot;

    @Value("${app.registry.address}")
    private String zkAddr;
}
