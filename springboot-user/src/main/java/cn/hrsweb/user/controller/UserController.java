package cn.hrsweb.user.controller;

import cn.hrsweb.user.pojo.User;
import cn.hrsweb.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String queryUserAll(Model model){
        // 查询用户
        List<User> users = userService.queryUserAll();
        // 放入模型
        model.addAttribute("users",users);
        // 返回模板名称(classpath:/templates/目录下html文件名)
        return "users";
    }
}
