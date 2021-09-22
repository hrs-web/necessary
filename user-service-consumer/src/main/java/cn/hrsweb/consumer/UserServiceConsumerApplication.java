package cn.hrsweb.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient  //开启Eureka的客户端
@EnableHystrix      //开启Hystrix熔断。@EnableCircuitBreaker以被弃用
//@SpringCloudApplication  // 组合注解，相当于SpringBootApplication和EnableDiscoveryClient
@EnableFeignClients  // 开启Feign注解
public class UserServiceConsumerApplication {
    @Bean
    @LoadBalanced  // 开启ribbon的负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceConsumerApplication.class, args);
    }

}
