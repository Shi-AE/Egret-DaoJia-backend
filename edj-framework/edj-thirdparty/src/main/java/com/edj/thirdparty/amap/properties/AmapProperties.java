package com.edj.thirdparty.amap.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author A.E.
 * @date 2024/9/23
 */
@Component
@ConfigurationProperties(prefix = "amap")
@ConditionalOnProperty(prefix = "amap", name = "enable", havingValue = "true")
@Data
public class AmapProperties {
    private String key;
}