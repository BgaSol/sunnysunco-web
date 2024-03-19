package com.sunnysunco.cloud.config.satoken;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.interceptor.SaInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class SaTokenConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器
        SaInterceptor saInterceptor = new SaInterceptor();
        // 打开注解式鉴权功能
        registry.addInterceptor(saInterceptor).addPathPatterns("/**");

    }

    @Bean
    public SaTokenConfig saTokenConfig() {
        SaTokenConfig config = new SaTokenConfig();
        // token 名称（同时也是 cookie 名称）
        config.setTokenName("authorization");
        // token 有效期（单位：秒） 默认30天，-1 代表永久有效
        config.setTimeout(30 * 24 * 60 * 60);
        // token 最低活跃频率（单位：秒），如果 token 超过此时间没有访问系统就会被冻结，默认-1 代表不限制，永不冻结
        config.setActiveTimeout(60 * 60);
        // 允许同账号多地登录
        config.setIsConcurrent(true);
        // 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)
        config.setIsShare(false);
        // token风格
        config.setTokenStyle("tik");
        // 输出日志
        return config;
    }
}