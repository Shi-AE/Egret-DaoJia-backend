package com.edj.gateway.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "gateway.api")
@Data
@Slf4j
public class ApiProperties {

    /**
     * 访问路径地址白名单
     */
    @NestedConfigurationProperty
    private final Set<String> accessPathWhiteList = new HashSet<>();

    /**
     * 访问路径黑名单
     */
    @NestedConfigurationProperty
    private final Set<String> accessPathBlackList = new HashSet<>();

}
