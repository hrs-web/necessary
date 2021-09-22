package cn.hrsweb.consumer.client;

import cn.hrsweb.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "PROVIDER-SERVER",fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("provider/{id}")
    public User queryUserByIdFeign(@PathVariable("id")Long id);
}
