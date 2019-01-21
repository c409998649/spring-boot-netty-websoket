package com.chen.server.zookeeper;

import com.chen.comm.util.Constant;
import com.chen.server.zookeeper.AppConfiguration;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Zookeeper工具类
 *
 * @author chenzhiying@zbj.com
 * @title Zookeeper工具类
 * @date 2019-01-17 上午10:36
 **/
@Component
public class ZookeeperUtil {

    private ZkClient zkClient;

    @Resource
    private AppConfiguration appConfiguration ;

    @Value("${app.registry.address}")
    public void ZookeeperUtil(String address){
        zkClient = new ZkClient(address, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
    }

    /**
     * 创建父级节点
     */
    public void createRootNode(){
        boolean exists = zkClient.exists("/"+appConfiguration.getZkRoot());
        if (exists){
            return;
        }

        //创建 root
        zkClient.createPersistent(appConfiguration.getZkRoot()) ;
    }

    /**
     * 写入指定节点 临时目录
     *
     * @param path
     */
    public void createNode(String path) {
        zkClient.createEphemeral(path);
    }
}
