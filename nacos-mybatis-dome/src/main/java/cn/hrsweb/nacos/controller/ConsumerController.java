package cn.hrsweb.nacos.controller;

import cn.hrsweb.nacos.client.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// 服务消费者
@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @Autowired
    private ProviderClient providerClient;

    @GetMapping("feign")
    @ResponseBody
    public String FeignTest(){
        return providerClient.getMessage();
    }
}
