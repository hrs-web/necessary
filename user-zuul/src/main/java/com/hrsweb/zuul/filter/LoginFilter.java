package com.hrsweb.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 过滤器的类型：pre，routing，post，error
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序，数字越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * 判断过滤器是否需要执行run方法
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 执行拦截的业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 1.获取zuul网关上下文对象
        RequestContext context = RequestContext.getCurrentContext();
        // 2.获取请求对象
        HttpServletRequest request = context.getRequest();
        // 3.获取请求参数
        String token = request.getParameter("token");
        // 4.判断如果没有登录，就拦截
        if (StringUtils.isBlank(token)){
            // 不再转发
            context.setSendZuulResponse(false);
            // 设置响应状态码
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            // 设置响应信息
            context.setResponseBody("request error");
        }
        return null;
    }
}
