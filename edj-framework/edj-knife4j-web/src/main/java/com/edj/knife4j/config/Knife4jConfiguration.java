package com.edj.knife4j.config;


import com.edj.common.utils.DateUtils;
import com.edj.knife4j.filter.SwaggerFilter;
import com.edj.knife4j.properties.SwaggerProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
@EnableKnife4j
@EnableConfigurationProperties({SwaggerProperties.class})
@Import(SwaggerFilter.class)
@ConditionalOnProperty(prefix = "swagger", name = "enable", havingValue = "true")
@RequiredArgsConstructor
public class Knife4jConfiguration {

    private final SwaggerProperties swaggerProperties;

    /**
     * 配置基本信息
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        // 标题
                        .title(swaggerProperties.getTitle())
                        // 描述Api接口文档的基本信息
                        .description(swaggerProperties.getDescription())
                        // 版本
                        .version(swaggerProperties.getVersion() + "\t部署时间\t" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_FORMAT)))
                        // 设置OpenAPI文档的联系信息，姓名，邮箱。
                        .contact(new Contact()
                                .name(swaggerProperties.getContactName())
                                .url(swaggerProperties.getContactUrl())
                                .email(swaggerProperties.getContactEmail())
                        )
                        // 设置OpenAPI文档的许可证信息，包括许可证名称为"Apache 2.0"，许可证URL为"http://springdoc.org"。
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }
}