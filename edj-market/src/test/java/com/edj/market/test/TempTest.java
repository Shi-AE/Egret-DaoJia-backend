package com.edj.market.test;

import com.edj.api.api.user.UserApi;
import com.edj.common.utils.AsyncUtils;
import com.edj.common.utils.ThreadUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.stream.IntStream;

import static com.edj.cache.constants.CacheConstants.CacheName.SUCCESS_SYNC_CACHE;
import static com.edj.market.constants.RedisConstants.LIMIT;
import static com.edj.market.constants.RedisConstants.QUEUE_NUM;

@SpringBootTest
@Slf4j
public class TempTest {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    @Autowired
    private UserApi userApi;

    @Test
    void test1() {
        // 分线程处理
        IntStream.range(0, QUEUE_NUM).forEach(i -> AsyncUtils.runAsync(() -> {
            // 获取key
            String key = String.format(SUCCESS_SYNC_CACHE, i);

            List<String> stringList = stringRedisTemplate.opsForList().rightPop(key, LIMIT);

            log.info("i: {}, stringList:{}", i, stringList);
        }));

        ThreadUtils.sleep(100000);
    }
}
