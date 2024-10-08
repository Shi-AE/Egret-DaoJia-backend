package com.edj.cache.config;

import com.edj.cache.aspect.HashCacheClearAspect;
import com.edj.cache.helper.CacheHelper;
import com.edj.cache.helper.LockHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author A.E.
 * @date 2024/9/28
 */
@Configuration
@Slf4j
@EnableConfigurationProperties(RedisProperties.class)
@Import({CacheHelper.class, LockHelper.class})
public class CacheConfiguration {

    @Bean
    public HashCacheClearAspect hashCacheClearAspect(CacheHelper cacheHelper) {
        return new HashCacheClearAspect(cacheHelper);
    }
}
