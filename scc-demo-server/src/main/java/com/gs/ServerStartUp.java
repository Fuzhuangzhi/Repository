package com.gs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 * @Date 2020/3/1 0001 9:57
 */
@SpringBootApplication
//@EnableConfigServer
public class ServerStartUp {
    public static void main(String[] args) {
        SpringApplication.run(ServerStartUp.class,args);
    }
}
