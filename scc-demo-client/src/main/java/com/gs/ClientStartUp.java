package com.gs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @Date 2020/3/1 0001 10:17
 */
@SpringBootApplication
@RestController
public class ClientStartUp {

    @Value("${guestname}")
    String name;

    @RequestMapping("/")
    public String sayHello(){
        return "Hello," + name;
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientStartUp.class, args);
    }
}
