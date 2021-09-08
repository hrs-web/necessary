package cn.hrsweb.user.service;

import cn.hrsweb.user.mapper.UserMapper;
import cn.hrsweb.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUserById(Long id){
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    public List<User> queryUserAll() {
        return userMapper.selectAll();
    }
}
