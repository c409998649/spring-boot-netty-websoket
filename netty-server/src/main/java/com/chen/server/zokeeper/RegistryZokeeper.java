package com.chen.server.zokeeper;

import com.chen.server.util.SpringBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 注册zokeeper
 * @author chenzhiying@zbj.com
 * @title 注册zokeeper
 * @date 2019-01-17 上午11:07
 **/
@Slf4j
public class RegistryZokeeper implements Runnable{

    private int port;

    private int serverPort;

    private ZokeeperUtil zokeeperUtil;

    private AppConfiguration appConfiguration;

    public RegistryZokeeper(int port, int serverPort){
        this.port = port;
        this.serverPort = serverPort;
        zokeeperUtil = SpringBeanFactory.getBean(ZokeeperUtil.class) ;
        appConfiguration = SpringBeanFactory.getBean(AppConfiguration.class) ;
    }

    @Override
    public void run() {
        //创建父节点
        zokeeperUtil.createRootNode();

        //是否要将自己注册到 ZK
        String path = null;
        try {
            path = "/"+appConfiguration.getZkRoot() + "/" + InetAddress.getLocalHost().getHostAddress()+":"+port+":"+serverPort;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        zokeeperUtil.createNode(path);
        log.info("注册 zookeeper 成功，msg=[{}]", path);
    }
}
