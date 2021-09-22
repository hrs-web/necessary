package cn.hrsweb.consumer.controller;

import cn.hrsweb.consumer.client.UserClient;
import cn.hrsweb.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("consumer")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;
    // eureka演示
    /*
        注意：当RestTemplate使用了负载均衡@LocalBlanced修饰之后这个方法就会出错
              因为必须使用PROVIDER-SERVER(应用名)替代instance.getHost():instance.getPort()即ip:端口
    */
    @GetMapping
    public User queryById(@PathParam("id")Long id){
        // 通过服务的id获取服务实例的集合
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER-SERVER");
        ServiceInstance instance = instances.get(0);
        System.out.println(instance.getHost());
        System.out.println(instance.getPort());
        return restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/provider/"+id,User.class);
    }
    // Ribbon演示
    @GetMapping("user")
    public List<User> queryByUserIds(@RequestParam(value = "ids",required=false)List<Long> ids){
        List<User> users = new ArrayList<>();
        ids.forEach(id -> {
            users.add(restTemplate.getForObject("http://PROVIDER-SERVER/provider/"+id,User.class));
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        return users;
    }

    // Hystrix演示
    @GetMapping("hystrix")
    @HystrixCommand(fallbackMethod = "queryByIdFallback")
    public String queryByIdHystrix(@PathParam("id")Long id){
        // 演示熔断后重试机制，id=50后出现异常服务熔断id等于其他的请求也不能用
        if (id==50){
            throw new RuntimeException();
        }
        return restTemplate.getForObject("http://PROVIDER-SERVER/provider/"+id,String.class);
    }

    // 局部方法调用此方法时，两者返回值和参数需要一致
    public String queryByIdFallback(Long id){
        return "服务器正忙，请稍后再试。";
    }


//-------------Feign集成了Ribbon和Hystrix，以后使用Feign就行--------------------------------
    @Autowired
    private UserClient userClient;

    @GetMapping("feign")
    public String queryByIdFeign(@PathParam("id")Long id){
        return userClient.queryUserByIdFeign(id).toString();
    }
}
