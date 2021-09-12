package cn.hrsweb.service.serivce;

import cn.hrsweb.service.mapper.UserMapper;
import cn.hrsweb.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUserId(Long id){
        return userMapper.selectByPrimaryKey(id);
    }
}
