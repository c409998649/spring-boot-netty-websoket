package com.chen.client.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-18 下午3:58
 **/
public class UserMap {

    private final static ConcurrentMap<String, UserInfo> USER_MAP = new ConcurrentHashMap<>(16);

    /**
     * 插入用户数据
     *
     * @param key key值
     * @param userInfo 数据
     * @return void
     * @title <一句话说明功能>
     * @author chenzhiying@zbj.com
     * @date 19-1-18
     * @since openJDK 1.8
     **/
    public static void put(String key, UserInfo userInfo){
        USER_MAP.put(key, userInfo);
    }

    /**
     * 获取用户数据
     *
     * @param key key值
     * @return com.chen.client.entity.UserInfo
     * @title 获取用户数据
     * @author chenzhiying@zbj.com
     * @date 19-1-18
     * @since openJDK 1.8
     **/
    public static UserInfo get(String key){
        return USER_MAP.get(key);
    }

    /**
     * 用户下线
     *
     * @param key key值
     * @return com.chen.client.entity.UserInfo
     * @title 获取用户数据
     * @author chenzhiying@zbj.com
     * @date 19-1-18
     * @since openJDK 1.8
     **/
    public static void remove(String key){
        USER_MAP.remove(USER_MAP.get(key));
    }

    /**
     * 在线用户数
     *
     * @return int
     * @title <一句话说明功能>
     * @author chenzhiying@zbj.com
     * @date 19-1-18
     * @since openJDK 1.8
     **/
    public static int size(){
        return USER_MAP.size();
    }

    /**
     * 循环所有在线用户
     *
     * @return java.util.List<com.chen.client.entity.UserInfo>
     * @title 循环所有在线用户
     * @author chenzhiying@zbj.com
     * @date 19-1-18
     * @since openJDK 1.8
     **/
    public static List<UserInfo> getList(){
        List<UserInfo> userInfos = new ArrayList<>();
        for (Map.Entry<String, UserInfo> entry : USER_MAP.entrySet()) {
            userInfos.add(entry.getValue());
        }
        return userInfos;
    }
}
