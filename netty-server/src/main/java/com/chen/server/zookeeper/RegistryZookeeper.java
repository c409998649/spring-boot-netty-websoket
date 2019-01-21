package com.chen.server.zookeeper;

import com.chen.server.util.SpringBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 注册Zookeeper
 * @author chenzhiying@zbj.com
 * @title 注册Zookeeper
 * @date 2019-01-17 上午11:07
 **/
@Slf4j
public class RegistryZookeeper implements Runnable{

    private int port;

    private int serverPort;

    private ZookeeperUtil ZookeeperUtil;

    private AppConfiguration appConfiguration;

    public RegistryZookeeper(int port, int serverPort){
        this.port = port;
        this.serverPort = serverPort;
        ZookeeperUtil = SpringBeanFactory.getBean(ZookeeperUtil.class) ;
        appConfiguration = SpringBeanFactory.getBean(AppConfiguration.class) ;
    }

    @Override
    public void run() {
        //创建父节点
        ZookeeperUtil.createRootNode();

        //是否要将自己注册到 ZK
        String path = null;
        try {
            path = "/"+appConfiguration.getZkRoot() + "/" + InetAddress.getLocalHost().getHostAddress()+":"+port+":"+serverPort;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ZookeeperUtil.createNode(path);
        log.info("注册 zookeeper 成功，msg=[{}]", path);
    }
}
