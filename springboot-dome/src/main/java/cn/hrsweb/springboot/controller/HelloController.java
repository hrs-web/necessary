package cn.hrsweb.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController   // 是@Controller和@ResponseBody的合体
@RequestMapping("hello")
@EnableAutoConfiguration
public class HelloController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("show")
    public String test(){
        return "hello spring boot! "+dataSource;
    }

}
