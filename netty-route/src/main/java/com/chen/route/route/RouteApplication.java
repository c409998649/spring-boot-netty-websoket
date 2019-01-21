package com.chen.route.route;

import com.chen.route.route.Zookeeper.ServerListListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RouteApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RouteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //监听服务
        Thread thread = new Thread(new ServerListListener());
        thread.setName("zk-listener");
        thread.start();
    }
}

