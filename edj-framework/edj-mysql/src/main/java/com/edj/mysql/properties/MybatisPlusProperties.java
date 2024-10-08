package com.edj.mysql.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author A.E.
 * @date 2024/9/20
 */
@Configuration
@ConfigurationProperties(prefix = "mybatis-plus")
@Data
public class MybatisPlusProperties {
    private Page page = new Page();

    @Data
    public static class Page {
        /**
         * 默认最大分页200条
         */
        private Long maxLimit = 200L;
    }
}
