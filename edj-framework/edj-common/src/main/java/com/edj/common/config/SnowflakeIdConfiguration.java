package com.edj.common.config;

import cn.hutool.core.lang.Snowflake;
import com.edj.common.utils.IdUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Data
@Slf4j
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "snowflake")
public class SnowflakeIdConfiguration {

    private Integer workerId = 1;

    private Integer datacenterId = 1;

    @Bean
    public Snowflake snowflake() {
        Snowflake snowflake = new Snowflake(
                new Date(IdUtils.DEFAULT_TWEPOCH),
                workerId,
                datacenterId,
                IdUtils.IS_USE_SYSTEM_CLOCK,
                IdUtils.DEFAULT_TIME_OFFSET,
                IdUtils.RANDOM_SEQUENCE_LIMIT
        );
        log.info("创建雪花id生成器: workerId={}, datacenterId={}", workerId, datacenterId);
        return snowflake;
    }
}
