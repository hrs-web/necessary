package cn.hrsweb.consumer.controller;

import cn.hrsweb.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("consumer")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public User queryById(@PathParam("id")Long id){
        return restTemplate.getForObject("http://localhost:8081/provider/"+id,User.class);
    }
}
