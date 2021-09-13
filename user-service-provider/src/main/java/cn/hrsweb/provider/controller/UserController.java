package cn.hrsweb.provider.controller;

import cn.hrsweb.provider.pojo.User;
import cn.hrsweb.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("provider")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User queryById(@PathVariable("id")Long id){
        return userService.queryById(id);
    }
}
