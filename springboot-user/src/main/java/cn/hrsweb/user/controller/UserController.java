package cn.hrsweb.user.controller;

import cn.hrsweb.user.pojo.User;
import cn.hrsweb.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    @ResponseBody
    public User queryUserById(@PathVariable("id")Long id){
        return userService.queryUserById(id);
    }

    @GetMapping("all")
    @ResponseBody
    public List<User> queryUserAll(){
        return userService.queryUserAll();
    }
}
