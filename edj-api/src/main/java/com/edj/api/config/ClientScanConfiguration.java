package com.edj.api.config;


import com.edj.api.interceptor.FeignInterceptor;
import com.edj.common.handler.RequestIdHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@EnableFeignClients(basePackages = "com.edj.api.api")
@Import({com.edj.api.utils.MyQueryMapEncoder.class})
@ConditionalOnProperty(prefix = "feign", name = "enable", havingValue = "true")
public class ClientScanConfiguration {
    @Bean
    public FeignInterceptor feignInterceptor(RequestIdHandler requestIdHandler) {
        return new FeignInterceptor(requestIdHandler);
    }
}
