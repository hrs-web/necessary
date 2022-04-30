package cn.hrsweb.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 模块化抽取api
@RequestMapping("provider")
public interface ProviderApi {
    @GetMapping("feign")
    String getMessage();
}
