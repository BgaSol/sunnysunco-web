package com.sunnysunco.cloud.config.openapi;


import cn.dev33.satoken.config.SaTokenConfig;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfiguration {

    private final SaTokenConfig saTokenConfig;

    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI();
        // 设置API文档的基本信息
        Info info = new Info().title("API文档").version("1.0.0").description("样本标注");
        openAPI.info(info);

        // 设置API文档的安全方案
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setType(SecurityScheme.Type.APIKEY);
        securityScheme.setIn(SecurityScheme.In.HEADER);
        securityScheme.setName(saTokenConfig.getTokenName());

        // 设置API文档的组件
        Components components = new Components();
        components.addSecuritySchemes("身份验证 Token", securityScheme);
        openAPI.components(components);
        return openAPI;
    }
}