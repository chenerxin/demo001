package com.example.demo001.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: TODO
 * @Author: erxin.chen
 * @Date: 2021/1/24 14:44
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer{
    @Autowired
    private RsaSignInterceptor rsaSignInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 签名拦截器
        registry.addInterceptor(rsaSignInterceptor).addPathPatterns("/**");
    }
}
