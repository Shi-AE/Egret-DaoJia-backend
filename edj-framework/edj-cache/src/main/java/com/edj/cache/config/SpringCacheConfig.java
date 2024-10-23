package com.edj.cache.config;

import com.edj.redis.config.FastJson2JsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.Random;

/**
 * SpringCache配置
 *
 * @author A.E.
 * @date 2024/9/20
 */
@Configuration
public class SpringCacheConfig {

//    private static final GenericJackson2JsonRedisSerializer JACKSON_SERIALIZER;
//
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//    static {
//        JavaTimeModule timeModule = new JavaTimeModule();
//        timeModule.addDeserializer(LocalDate.class,
//                new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_FORMAT)));
//        timeModule.addDeserializer(LocalTime.class,
//                new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_TIME_FORMAT)));
//        timeModule.addDeserializer(LocalDateTime.class,
//                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_FORMAT)));
//        timeModule.addSerializer(LocalDate.class,
//                new LocalDateSerializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_FORMAT)));
//        timeModule.addSerializer(LocalTime.class,
//                new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_TIME_FORMAT)));
//        timeModule.addSerializer(LocalDateTime.class,
//                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_FORMAT)));
//
//        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//
//        OBJECT_MAPPER.activateDefaultTyping(
//                LaissezFaireSubTypeValidator.instance,
//                ObjectMapper.DefaultTyping.NON_CONCRETE_AND_ARRAYS,
//                JsonTypeInfo.As.PROPERTY
//        );
//
////        OBJECT_MAPPER.deactivateDefaultTyping();
//
//        OBJECT_MAPPER.registerModule(timeModule);
//
//        // 定义Jackson类型序列化对象
//        JACKSON_SERIALIZER = new GenericJackson2JsonRedisSerializer(OBJECT_MAPPER);
//    }

    /**
     * 缓存时间30分钟
     *
     * @param connectionFactory redis连接工厂
     * @return redis缓存管理器
     */
    @Bean
    public CacheManager cacheManager30Minutes(RedisConnectionFactory connectionFactory) {
        int randomNum = new Random().nextInt(100);
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .computePrefixWith(cacheName -> cacheName + ":")
                .entryTtl(Duration.ofSeconds(30 * 60L + randomNum))
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                new FastJson2JsonRedisSerializer<>(Object.class)
                        )
                );

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
    }

    /**
     * 缓存时间1天
     *
     * @param connectionFactory redis连接工厂
     * @return redis缓存管理器
     */
    @Bean
    public CacheManager cacheManagerOneDay(RedisConnectionFactory connectionFactory) {
        //生成随机数
        int randomNum = new Random().nextInt(6000);
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .computePrefixWith(cacheName -> cacheName + ":")
                //过期时间为基础时间加随机数
                .entryTtl(Duration.ofSeconds(24 * 60 * 60L + randomNum))
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                new FastJson2JsonRedisSerializer<>(Object.class)
                        )
                );

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
    }

    /**
     * 永久缓存
     *
     * @param connectionFactory redis连接工厂
     * @return redis缓存管理器
     */
    @Bean
    @Primary
    public CacheManager cacheManagerForever(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .computePrefixWith(cacheName -> cacheName + ":")
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                new FastJson2JsonRedisSerializer<>(Object.class)
                        )
                );

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
    }
}


