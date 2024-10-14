package com.edj.thirdparty.ali.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author A.E.
 * @date 2024/9/23
 */
@Data
@Component
@ConfigurationProperties(prefix = "ali.oss")
@ConditionalOnProperty(prefix = "ali.oss", name = "enable", havingValue = "true")
public class AliOssProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String basePath;
}