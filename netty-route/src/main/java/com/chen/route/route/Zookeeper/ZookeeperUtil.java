package com.chen.route.route.Zookeeper;

import com.alibaba.fastjson.JSON;
import com.chen.comm.util.Constant;
import com.chen.route.route.cache.ServerCache;
import com.chen.route.route.util.SpringBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-17 下午2:27
 **/
@Slf4j
@Component
public class ZookeeperUtil {

    private ZkClient zkClient;

    @Resource
    private ServerCache serverCache;

    @Value("${app.registry.address}")
    public void ZookeeperUtil(String address){
        zkClient = new ZkClient(address, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
    }

    /**
     * 监听事件
     *
     * @param path
     */
    public void subscribeEvent(String path) {
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                log.info("清除/更新本地缓存 parentPath=【{}】,currentChilds=【{}】", parentPath,currentChilds.toString());

                //更新所有缓存/先删除 再新增
                serverCache.updateCache(currentChilds) ;
            }
        });


    }


    /**
     * 获取所有注册节点
     * @return
     */
    public List<String> getAllNode(){
        List<String> children = zkClient.getChildren("/route");
        log.info("查询所有节点成功=【{}】", JSON.toJSONString(children));
        return children;
    }
}
