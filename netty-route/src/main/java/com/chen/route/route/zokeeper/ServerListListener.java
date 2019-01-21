package com.chen.route.route.zokeeper;

import com.chen.route.route.util.SpringBeanFactory;

/**
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-17 下午2:32
 **/
public class ServerListListener  implements Runnable{

    private ZokeeperUtil zokeeperUtil;

    private AppConfiguration appConfiguration ;


    public ServerListListener() {
        zokeeperUtil = SpringBeanFactory.getBean(ZokeeperUtil.class) ;
        appConfiguration = SpringBeanFactory.getBean(AppConfiguration.class) ;
    }

    @Override
    public void run() {
        //注册监听服务
        zokeeperUtil.subscribeEvent("/"+appConfiguration.getZkRoot());
    }
}
