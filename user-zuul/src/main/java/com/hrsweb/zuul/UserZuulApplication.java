package com.hrsweb.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy   // 开启Zuul网关的代理功能
@EnableEurekaClient  // 开启Eureka客户端
public class UserZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserZuulApplication.class, args);
    }

}
