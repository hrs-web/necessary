package cn.hrsweb.service.controller;

import cn.hrsweb.service.pojo.User;
import cn.hrsweb.service.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User queryUserId(@PathVariable("id")Long id){
        return userService.queryUserId(id);
    }
}
