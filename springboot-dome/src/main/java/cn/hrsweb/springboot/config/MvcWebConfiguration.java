package cn.hrsweb.springboot.config;

import cn.hrsweb.springboot.intercept.MyIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 */
@Configuration
public class MvcWebConfiguration implements WebMvcConfigurer {
    @Autowired
    private MyIntercept intercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(intercept).addPathPatterns("/**");
    }
}
