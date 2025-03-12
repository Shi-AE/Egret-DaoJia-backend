package com.edj.market.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.api.api.user.UserApi;
import com.edj.common.utils.AsyncUtils;
import com.edj.common.utils.CollUtils;
import com.edj.common.utils.ThreadUtils;
import com.edj.market.domain.entity.EdjActivity;
import com.edj.market.enums.EdjActivityStatus;
import com.edj.market.mapper.EdjActivityMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static com.edj.cache.constants.CacheConstants.CacheName.*;
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

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    @Autowired
    private EdjActivityMapper activityMapper;

    @Test
    void test2() {
        // 清理抢券成功队列缓存
        // 获取所有key
        String prefix = SUCCESS_LIST_CACHE.substring(0, SUCCESS_LIST_CACHE.length() - 7);
        Set<String> successKeys = redisTemplate.keys(prefix.concat("*"));

        if (CollUtils.isNotEmpty(successKeys)) {
            // 匹配所有活动id
            Pattern pattern = Pattern.compile("^" + prefix + "(\\d+)_\\{\\d+}$");
            List<String> activityIdList = successKeys
                    .stream()
                    .map(key -> {
                        Matcher matcher = pattern.matcher(key);
                        if (matcher.find()) {
                            return matcher.group(1);
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .toList();
            // 校验活动是否结束
            LambdaQueryWrapper<EdjActivity> wrapper = new LambdaQueryWrapper<EdjActivity>()
                    .select(EdjActivity::getId)
                    .in(EdjActivity::getStatus, List.of(EdjActivityStatus.EXPIRED, EdjActivityStatus.CANCELLED))
                    .in(EdjActivity::getId, activityIdList);
            List<EdjActivity> activityEndList = activityMapper.selectList(wrapper);
            // 清除缓存
            List<String> activityEndIdList = activityEndList
                    .stream()
                    .map(EdjActivity::getId)
                    .map(id -> String.format(SUCCESS_LIST_CACHE, id, id % QUEUE_NUM))
                    .toList();
            redisTemplate.delete(activityEndIdList);
        }
    }
}
