package cn.hrsweb.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  // 启动eureka的服务端
public class UserServiceEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceEurekaApplication.class, args);
    }

}
