package com.sunnysunco.cloud.config.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 初始化MybatisPlus拦截器
        return new MybatisPlusInterceptor();
    }
}
