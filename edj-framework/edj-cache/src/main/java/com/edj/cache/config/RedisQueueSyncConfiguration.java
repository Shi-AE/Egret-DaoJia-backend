package com.edj.cache.config;

import com.edj.cache.properties.RedisSyncProperties;
import com.edj.cache.sync.impl.SyncManagerImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(RedisSyncProperties.class)
@Import(SyncManagerImpl.class)
public class RedisQueueSyncConfiguration {
}
