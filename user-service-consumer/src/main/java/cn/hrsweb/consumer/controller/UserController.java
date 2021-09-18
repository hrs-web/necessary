package cn.hrsweb.consumer.controller;

import cn.hrsweb.consumer.pojo.User;
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

}
