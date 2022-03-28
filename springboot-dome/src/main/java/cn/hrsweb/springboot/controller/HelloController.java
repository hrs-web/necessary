package cn.hrsweb.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@RestController   // 是@Controller和@ResponseBody的合体
@RequestMapping("hello")
@EnableAutoConfiguration
public class HelloController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("show")
    public String test(){
        return "hello spring"+dataSource;
    }

    @PostMapping("post")
    public void post(){
        System.out.println("执行了post方法");
    }

    @PutMapping("put")
    public void put(){
        System.out.println("执行了put方法");
    }

    @DeleteMapping("delete")
    public void delete(){
        System.out.println("执行了delete方法");
    }

}
