package edj.orders.grab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * redis + lua 脚本配置
 *
 * @author A.E.
 * @date 2025/4/26
 */
@Configuration
public class RedisLuaConfiguration {

    @Bean
    public DefaultRedisScript<Long> grabOrdersScript() {
        DefaultRedisScript<Long> grabCouponScript = new DefaultRedisScript<>();
        grabCouponScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("scripts/lua/grabOrdersScript.lua")));
        grabCouponScript.setResultType(Long.class);
        return grabCouponScript;
    }
}
