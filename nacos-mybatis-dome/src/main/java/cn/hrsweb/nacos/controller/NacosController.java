package cn.hrsweb.nacos.controller;

import cn.hrsweb.nacos.mapper.UserMapper;
import cn.hrsweb.nacos.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NacosController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有做核酸的班级
     * @return
     */
    @GetMapping("/mybatis")
    public List<User> getAll(){
        List<User> users = this.userMapper.selectList(null);
        return users;
    }

    /**
     * 根据班级查询测核酸时间
     * @param grade
     * @return
     */
    @GetMapping("/mybatis/{grade}")
    public List<User> getUser(@PathVariable(value = "grade")String grade){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //like %?% likeLeft %? 以xxx结尾  LikeRight ?%  以xxx开头
        queryWrapper.like("grade",grade);
        List<User> users = this.userMapper.selectList(queryWrapper);
        return users;
    }
}
