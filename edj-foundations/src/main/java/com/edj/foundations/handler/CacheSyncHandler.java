package com.edj.foundations.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.common.utils.AsyncUtils;
import com.edj.common.utils.CollUtils;
import com.edj.foundations.domain.entity.EdjRegion;
import com.edj.foundations.enums.EdjRegionActiveStatus;
import com.edj.foundations.mapper.EdjRegionMapper;
import com.edj.foundations.service.ConsumerHomeService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static com.edj.cache.constants.CacheConstants.CacheName.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheSyncHandler {

    private final StringRedisTemplate redisTemplate;

    private final ConsumerHomeService consumerHomeService;

    private final EdjRegionMapper regionMapper;

    /**
     * 定时更新已开通服务区域列表缓存
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

    /**
     * 定时更新首页服务列表缓存
     * 每日凌晨1点执行
     */
    @XxlJob("HomeCategoryCacheSync")
    public void homeCategoryCacheSync() {
        log.info(">>>>>>>> 开始进行缓存同步，更新首页服务列表缓存");
        // 删除所有区域缓存
        Set<String> keys = redisTemplate.keys(HOME_CATEGORY_CACHE.concat("*"));
        if (CollUtils.isEmpty(keys)) {
            return;
        }
        redisTemplate.delete(keys);

        // 获取所有已启用区域
        LambdaQueryWrapper<EdjRegion> wrapper = new LambdaQueryWrapper<EdjRegion>()
                .select(EdjRegion::getId)
                .eq(EdjRegion::getActiveStatus, EdjRegionActiveStatus.ENABLED);
        List<EdjRegion> regionList = regionMapper.selectList(wrapper);
        if (CollUtils.isEmpty(regionList)) {
            return;
        }

        // 执行查询获取缓存
        regionList.stream().map(EdjRegion::getId)
                .forEach(id -> AsyncUtils.runAsync(() -> consumerHomeService.getServeIconCategoryByRegionIdCache(id)));

        log.info(">>>>>>>>更新首页服务列表缓存完成");
    }

    /**
     * 定时更新首页服务类型列表缓存
     * 每日凌晨1点执行
     */
    @XxlJob("HomeServeTypeCache")
    public void homeServeTypeCacheSync() {
        log.info(">>>>>>>> 开始进行缓存同步，更新首页服务类型列表缓存");
        // 删除所有区域缓存
        Set<String> keys = redisTemplate.keys(HOME_SERVE_TYPE_CACHE.concat("*"));
        if (CollUtils.isEmpty(keys)) {
            return;
        }
        redisTemplate.delete(keys);

        // 获取所有已启用区域
        LambdaQueryWrapper<EdjRegion> wrapper = new LambdaQueryWrapper<EdjRegion>()
                .select(EdjRegion::getId)
                .eq(EdjRegion::getActiveStatus, EdjRegionActiveStatus.ENABLED);
        List<EdjRegion> regionList = regionMapper.selectList(wrapper);
        if (CollUtils.isEmpty(regionList)) {
            return;
        }

        // 执行查询获取缓存
        regionList.stream().map(EdjRegion::getId)
                .forEach(id -> AsyncUtils.runAsync(() -> consumerHomeService.serveTypeListByRegionId(id)));

        log.info(">>>>>>>>更新首页服务类型列表缓存完成");
    }

    /**
     * 定时更新首页区域热门服务缓存
     * 每日凌晨1点执行
     */
    @XxlJob("HomeHotServeCache")
    public void homeHotServeCacheSync() {
        log.info(">>>>>>>> 开始进行缓存同步，更新首页区域热门服务缓存");
        // 删除所有区域缓存
        Set<String> keys = redisTemplate.keys(HOME_HOT_SERVE_CACHE.concat("*"));
        if (CollUtils.isEmpty(keys)) {
            return;
        }
        redisTemplate.delete(keys);

        // 获取所有已启用区域
        LambdaQueryWrapper<EdjRegion> wrapper = new LambdaQueryWrapper<EdjRegion>()
                .select(EdjRegion::getId)
                .eq(EdjRegion::getActiveStatus, EdjRegionActiveStatus.ENABLED);
        List<EdjRegion> regionList = regionMapper.selectList(wrapper);
        if (CollUtils.isEmpty(regionList)) {
            return;
        }

        // 执行查询获取缓存
        regionList.stream().map(EdjRegion::getId)
                .forEach(id -> AsyncUtils.runAsync(() -> consumerHomeService.getHotByRegionId(id)));

        log.info(">>>>>>>>更新首页区域热门服务缓存完成");
    }
}
