package com.sunnysunco.cloud.config;

import com.sunnysunco.cloud.business.information.log.LogInterceptors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class GlobalRequestHandler implements WebMvcConfigurer {

    private final LogInterceptors logInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(logInterceptors).addPathPatterns("/**");
    }
}
