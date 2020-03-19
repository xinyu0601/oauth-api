package com.xin.oauth.config;

import com.xin.oauth.aop.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xinyu.huang02
 * @date 2020-03-05 12:35
 * @class 拦截器配置
 */

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

    @Bean
    public TokenInterceptor getTokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getTokenInterceptor());
    }
}
