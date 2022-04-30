package cn.hrsweb.feign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// 服务提供者
@RestController
@RequestMapping("provider")
public class ProviderController {

    @GetMapping("feign")
    @ResponseBody
    public String getMessage(){
        System.out.println("hello.....");
        return "feign模块化：黄日升";
    }
}
