package com.edj.redis.filter;

import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;

/**
 * 强制排除自动装箱的StringRedisTemplate和RedisTemplate两个模板类，使用自定义的RedisTemplate
 *
 * @author A.E.
 * @date 2024/9/20
 */
public class RedisAutoConfigurationImportFilter implements AutoConfigurationImportFilter {

    private static final String REDIS_AUTO_CONFIGURATION_CLASS_PATH = "org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration";

    @Override
    public boolean[] match(String[] autoConfigurationClassNames, AutoConfigurationMetadata autoConfigurationMetadata) {
        boolean[] matches = new boolean[autoConfigurationClassNames.length];

        for (int count = 0; count < autoConfigurationClassNames.length; count++) {
            String className = autoConfigurationClassNames[count];
            matches[count] = !REDIS_AUTO_CONFIGURATION_CLASS_PATH.equals(className);
        }
        return matches;
    }
}
