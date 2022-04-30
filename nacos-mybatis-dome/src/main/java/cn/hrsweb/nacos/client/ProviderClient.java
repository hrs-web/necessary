package cn.hrsweb.nacos.client;

import cn.hrsweb.api.ProviderApi;
import org.springframework.cloud.openfeign.FeignClient;

// 拉取feign-server服务
@FeignClient("feign-server")
public interface ProviderClient extends ProviderApi {
}
