package com.edj.mysql.config;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * mp id生成配置
 *
 * @author A.E.
 * @date 2024/10/12
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SnowflakeIdGeneratorConfiguration implements IdentifierGenerator {

    private final Snowflake snowflake;

    @Override
    public Number nextId(Object entity) {
        return snowflake.nextId();
    }
}
