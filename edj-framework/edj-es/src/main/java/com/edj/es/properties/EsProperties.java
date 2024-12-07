package com.edj.es.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * es配置
 *
 * @author A.E.
 * @date 2024/12/7
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "es")
public class EsProperties {
    /**
     * es host
     */
    private String host;
    /**
     * es 端口
     */
    private Integer port;
}
