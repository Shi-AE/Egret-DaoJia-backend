package com.edj.knife4j.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * swagger 配置属性
 *
 * @author A.E.
 * @date 2024/9/19
 */
@Data
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    /**
     * swagger文档标题
     */
    private String title;
    /**
     * 应用描述
     */
    private String description;
    /**
     * 联系人名称
     */
    private String contactName;
    /**
     * 联系人访问地址
     */
    private String contactUrl;
    /**
     * 联系人email
     */
    private String contactEmail;
    /**
     * 版本号
     */
    private String version;
}
