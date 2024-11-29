package com.edj.foundations.handler;

import com.edj.foundations.service.ConsumerHomeService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import static com.edj.cache.constants.CacheConstants.CacheName.ACTIVE_REGION_CACHE;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheSyncHandler {

    private final StringRedisTemplate redisTemplate;

    private final ConsumerHomeService consumerHomeService;

    /**
     * 已开通服务区域列表
     * 每日凌晨1点执行
     */
    @XxlJob(value = "ActiveRegionCacheSync")
    public void activeRegionCacheSync() {
        log.info(">>>>>>>> 开始进行缓存同步，更新已开通服务区域列表");
        //1.清理缓存
        String key = ACTIVE_REGION_CACHE + ":ActiveRegion";
        redisTemplate.delete(key);

        //2.刷新缓存
        consumerHomeService.getActiveRegionList();
        log.info(">>>>>>>> 更新已开通服务区域列表完成");
    }
}
