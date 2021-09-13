package cn.hrsweb.provider.service;

import cn.hrsweb.provider.mapper.UserMapper;
import cn.hrsweb.provider.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id){
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }
}
