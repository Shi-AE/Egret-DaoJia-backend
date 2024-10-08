package com.edj.mysql.config;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.edj.common.utils.IdUtils;
import com.edj.common.utils.Ipv4Utils;
import com.edj.common.utils.StringUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SnowflakeIdGeneratorConfiguration implements IdentifierGenerator {

    private final Environment environment;

    private Snowflake snowflake;


    @PostConstruct
    void setSnowflake() {
        String ipStr;
        try {
            ipStr = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            ipStr = "127.0.0.1";
        }
        long datacenterId = Ipv4Utils.ipv4ToLong(ipStr);
        long workerId = Long.parseLong(StringUtils.nullToDefault(environment.getProperty("server.port"), "35500"));
        snowflake = IdUtils.getSnowflake(
                workerId % 32,
                datacenterId % 32
        );
        log.info("创建雪花id生成器: workerId={}, datacenterId={}", workerId % 32, datacenterId % 32);
    }

    @Override
    public Number nextId(Object entity) {
        return snowflake.nextId();
    }
}
