package cn.hrsweb.consumer.client;

import cn.hrsweb.consumer.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public User queryUserByIdFeign(Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("服务器正忙，请稍后再试。");
        return user;
    }
}
