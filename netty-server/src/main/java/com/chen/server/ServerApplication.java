package com.chen.server;

import com.chen.server.zokeeper.RegistryZokeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ImportResource(locations= {"classpath:spring.xml"})
public class ServerApplication implements CommandLineRunner {

    @Value("${app.protocol.port}")
    private int port;

    @Value("${server.port}")
    private int serverPort;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Thread thread = new Thread(new RegistryZokeeper(port, serverPort));
        thread.setName("registry-zk");
        thread.start();
    }
}

