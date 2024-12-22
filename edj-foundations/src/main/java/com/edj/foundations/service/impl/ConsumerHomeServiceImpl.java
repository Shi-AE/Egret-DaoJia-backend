package com.edj.foundations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.api.api.foundations.dto.ServeItemCategoryDTO;
import com.edj.api.api.foundations.dto.ServeTypeCategoryDTO;
import com.edj.common.utils.CollUtils;
import com.edj.foundations.domain.entity.*;
import com.edj.foundations.domain.vo.*;
import com.edj.foundations.enums.EdjRegionActiveStatus;
import com.edj.foundations.enums.EdjServeIsHot;
import com.edj.foundations.enums.EdjServeItemActiveStatus;
import com.edj.foundations.enums.EdjServeSaleStatus;
import com.edj.foundations.mapper.EdjRegionMapper;
import com.edj.foundations.mapper.EdjServeMapper;
import com.edj.foundations.service.ConsumerHomeService;
import com.edj.foundations.service.EdjServeItemService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.edj.cache.constants.CacheConstants.CacheManager.THIRTY_MINUTES;
import static com.edj.cache.constants.CacheConstants.CacheName.*;

/**
 * 用户端首页服务实现
 *
 * @author A.E.
 * @date 2024/11/4
 */
@Service
@RequiredArgsConstructor
public class ConsumerHomeServiceImpl implements ConsumerHomeService {

    private final EdjRegionMapper regionMapper;

    private final EdjServeMapper serveMapper;

    private final EdjServeItemService serveItemService;

    @Override
    @Cacheable(cacheNames = ACTIVE_REGION_CACHE, key = "'ActiveRegion'")
    public List<RegionSimpleVO> getActiveRegionList() {

        MPJLambdaWrapper<EdjRegion> wrapper = new MPJLambdaWrapper<EdjRegion>()
                .select(EdjRegion::getId, EdjRegion::getName)
                .select(EdjCity::getCityCode)
                .selectAs(EdjCity::getId, RegionSimpleVO::getCityId)
                .innerJoin(EdjCity.class, EdjCity::getId, EdjRegion::getEdjCityId)
                .eq(EdjRegion::getActiveStatus, EdjRegionActiveStatus.ENABLED)
                .orderByAsc(EdjRegion::getSortNum);

        return regionMapper.selectJoinList(RegionSimpleVO.class, wrapper);
    }

    @Override
    @Caching(cacheable = {
            // 非空结果永久保存
            @Cacheable(cacheNames = HOME_CATEGORY_CACHE, key = "#regionId", unless = "#result.isEmpty()"),
            // 空结果缓存30分钟
            @Cacheable(cacheNames = HOME_CATEGORY_CACHE, key = "#regionId", unless = "!#result.isEmpty()", cacheManager = THIRTY_MINUTES)
    })
    public List<ServeCategoryVO> getServeIconCategoryByRegionIdCache(Long regionId) {

        // 检查区域启用
        LambdaQueryWrapper<EdjRegion> check = new LambdaQueryWrapper<EdjRegion>()
                .select(EdjRegion::getId)
                .eq(EdjRegion::getId, regionId)
                .eq(EdjRegion::getActiveStatus, EdjRegionActiveStatus.ENABLED);
        boolean exists = regionMapper.exists(check);
        if (!exists) {
            return CollUtils.emptyList();
        }

        MPJLambdaWrapper<EdjServe> wrapper = new MPJLambdaWrapper<EdjServe>()
                .selectAs(EdjServeType::getId, ServeCategoryVO::getServeTypeId)
                .selectAs(EdjServeType::getName, ServeCategoryVO::getServeTypeName)
                .selectAs(EdjServeType::getIcon, ServeCategoryVO::getServeTypeIcon)
                .selectAs(EdjServeType::getSortNum, ServeCategoryVO::getServeTypeSortNum)
                .selectCollection(ServeCategoryVO::getServeIconVOList, map -> map
                        .id(EdjServeType::getId)
                        .result(EdjServe::getId)
                        .result(EdjServeItem::getId, ServeIconVO::getServeItemId)
                        .result(EdjServeItem::getName, ServeIconVO::getServeItemName)
                        .result(EdjServeItem::getIcon, ServeIconVO::getServeItemIcon)
                        .result(EdjServeItem::getSortNum, ServeIconVO::getServeItemSortNum))
                .innerJoin(EdjServeItem.class, EdjServeItem::getId, EdjServe::getEdjServeItemId)
                .innerJoin(EdjServeType.class, EdjServeType::getId, EdjServeItem::getEdjServeTypeId)
                .eq(EdjServe::getEdjRegionId, regionId)
                .eq(EdjServe::getSaleStatus, EdjServeSaleStatus.PUBLISHED)
                .orderByAsc(EdjServeType::getSortNum)
                .orderByDesc(EdjServeType::getId)
                .orderByAsc(EdjServeItem::getSortNum)
                .orderByAsc(EdjServeItem::getId);

        List<ServeCategoryVO> serveCategoryVOList = serveMapper.selectJoinList(ServeCategoryVO.class, wrapper);

        return serveCategoryVOList
                .stream()
                .limit(2)
                .peek(x -> x.setServeIconVOList(x.getServeIconVOList()
                        .stream()
                        .limit(4)
                        .toList()))
                .toList();
    }

    @Override
    @Caching(cacheable = {
            // 非空结果永久保存
            @Cacheable(cacheNames = HOME_HOT_SERVE_CACHE, key = "#regionId", unless = "#result.isEmpty()"),
            // 空结果缓存30分钟
            @Cacheable(cacheNames = HOME_HOT_SERVE_CACHE, key = "#regionId", unless = "!#result.isEmpty()", cacheManager = THIRTY_MINUTES)
    })
    public List<ServeAggregationSimpleVO> getHotByRegionId(Long regionId) {

        // 检查区域启用
        LambdaQueryWrapper<EdjRegion> check = new LambdaQueryWrapper<EdjRegion>()
                .select(EdjRegion::getId)
                .eq(EdjRegion::getId, regionId)
                .eq(EdjRegion::getActiveStatus, EdjRegionActiveStatus.ENABLED);
        boolean exists = regionMapper.exists(check);
        if (!exists) {
            return CollUtils.emptyList();
        }

        MPJLambdaWrapper<EdjServe> wrapper = new MPJLambdaWrapper<EdjServe>()
                .select(EdjServe::getId, EdjServe::getPrice)
                .selectAs(EdjServe::getEdjServeItemId, ServeAggregationSimpleVO::getServeItemId)
                .selectAs(EdjServeItem::getName, ServeAggregationSimpleVO::getServeItemName)
                .selectAs(EdjServeItem::getImg, ServeAggregationSimpleVO::getServeItemImg)
                .selectAs(EdjServeItem::getUnit, ServeAggregationSimpleVO::getUnit)
                .selectAs(EdjServeItem::getDetailImg, ServeAggregationSimpleVO::getDetailImg)
                .innerJoin(EdjServeItem.class, EdjServeItem::getId, EdjServe::getEdjServeItemId)
                .eq(EdjServe::getIsHot, EdjServeIsHot.HOT)
                .eq(EdjServe::getSaleStatus, EdjServeSaleStatus.PUBLISHED)
                .eq(EdjServe::getEdjRegionId, regionId)
                .orderByDesc(EdjServe::getId);

        return serveMapper.selectJoinList(ServeAggregationSimpleVO.class, wrapper);
    }

    @Override
    public List<ServeTypeCategoryDTO> getActiveServeItemCategory() {
        MPJLambdaWrapper<EdjServeItem> wrapper = new MPJLambdaWrapper<EdjServeItem>()
                .selectAs(EdjServeType::getId, ServeCategoryVO::getServeTypeId)
                .selectAs(EdjServeType::getName, ServeCategoryVO::getServeTypeName)
                .selectCollection(ServeTypeCategoryDTO::getServeItemCategoryDTOList, map -> map
                        .result(EdjServeItem::getId, ServeItemCategoryDTO::getServeItemId)
                        .result(EdjServeItem::getName, ServeItemCategoryDTO::getServeItemName))
                .innerJoin(EdjServeType.class, EdjServeType::getId, EdjServeItem::getEdjServeTypeId)
                .eq(EdjServeItem::getActiveStatus, EdjServeItemActiveStatus.ENABLED)
                .orderByAsc(EdjServeType::getSortNum)
                .orderByDesc(EdjServeType::getId)
                .orderByAsc(EdjServeItem::getSortNum)
                .orderByAsc(EdjServeItem::getId);
        return serveItemService.selectJoinList(ServeTypeCategoryDTO.class, wrapper);
    }

    @Override
    @Caching(cacheable = {
            // 非空结果永久保存
            @Cacheable(cacheNames = HOME_SERVE_TYPE_CACHE, key = "#regionId", unless = "#result.isEmpty()"),
            // 空结果缓存30分钟
            @Cacheable(cacheNames = HOME_SERVE_TYPE_CACHE, key = "#regionId", unless = "!#result.isEmpty()", cacheManager = THIRTY_MINUTES)
    })
    public List<ServeTypeSimpleVo> serveTypeListByRegionId(Long regionId) {

        MPJLambdaWrapper<EdjServe> wrapper = new MPJLambdaWrapper<EdjServe>()
                .selectAs(EdjServeType::getId, ServeTypeSimpleVo::getServeTypeId)
                .selectAs(EdjServeType::getName, ServeTypeSimpleVo::getServeTypeName)
                .selectAs(EdjServeType::getImg, ServeTypeSimpleVo::getServeTypeImg)
                .selectAs(EdjServeType::getSortNum, ServeTypeSimpleVo::getServeTypeSortNum)
                .innerJoin(EdjServeItem.class, EdjServeItem::getId, EdjServe::getEdjServeItemId)
                .innerJoin(EdjServeType.class, EdjServeType::getId, EdjServeItem::getEdjServeTypeId)
                .eq(EdjServe::getEdjRegionId, regionId)
                .eq(EdjServe::getSaleStatus, EdjServeSaleStatus.PUBLISHED)
                .groupBy(EdjServeType::getId)
                .orderByAsc(EdjServeType::getSortNum)
                .orderByDesc(EdjServeType::getId);

        return serveMapper.selectJoinList(ServeTypeSimpleVo.class, wrapper);
    }
}
