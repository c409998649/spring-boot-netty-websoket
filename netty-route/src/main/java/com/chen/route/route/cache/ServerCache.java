package com.chen.route.route.cache;

import com.chen.route.route.Zookeeper.ZookeeperUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 服务器节点缓存
 *
 * @author chenzhiying@zbj.com
 * @title 服务器节点缓存
 * @date 2019-01-17 下午2:26
 **/
@Component
public class ServerCache {

    private LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
        @Override
        public String load(String s) throws Exception {
            return null;
        }
    });

    @Resource
    private ZookeeperUtil ZookeeperUtil;

    private AtomicLong index = new AtomicLong();


    public void addCache(String key) {
        cache.put(key, key);
    }


    /**
     * 更新所有缓存/先删除 再新增
     *
     * @param currentChilds
     */
    public void updateCache(List<String> currentChilds) {
        cache.invalidateAll();
        for (String currentChild : currentChilds) {
            String key = currentChild.split("-")[1];
            addCache(key);
        }
    }


    /**
     * 获取所有的服务列表
     *
     * @return
     */
    public List<String> getAll() {

        List<String> list = new ArrayList<>();

        if (cache.size() == 0) {
            List<String> allNode = ZookeeperUtil.getAllNode();
            for (String node : allNode) {
                if(!node.equals("route")){
                    addCache(node);
                }
            }
        }
        for (Map.Entry<String, String> entry : cache.asMap().entrySet()) {
            list.add(entry.getKey());
        }
        return list;

    }

    /**
     * 选取服务器
     *
     * @return
     */
    public String selectServer() {
        List<String> all = getAll();
        if (all.size() == 0) {
            throw new RuntimeException("CIM 服务器可用服务列表为空");
        }
        Long position = index.incrementAndGet() % all.size();
        if (position < 0) {
            position = 0L;
        }

        return all.get(position.intValue());
    }
}
