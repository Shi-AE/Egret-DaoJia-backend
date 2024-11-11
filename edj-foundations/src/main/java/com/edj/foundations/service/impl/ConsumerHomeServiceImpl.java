package com.edj.foundations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.common.utils.CollUtils;
import com.edj.foundations.domain.entity.*;
import com.edj.foundations.domain.vo.RegionSimpleVO;
import com.edj.foundations.domain.vo.ServeAggregationSimpleVO;
import com.edj.foundations.domain.vo.ServeCategoryVO;
import com.edj.foundations.domain.vo.ServeIconVO;
import com.edj.foundations.enums.EdjRegionActiveStatus;
import com.edj.foundations.enums.EdjServeIsHot;
import com.edj.foundations.enums.EdjServeSaleStatus;
import com.edj.foundations.mapper.EdjRegionMapper;
import com.edj.foundations.mapper.EdjServeMapper;
import com.edj.foundations.service.ConsumerHomeService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<RegionSimpleVO> getActiveRegionList() {

        MPJLambdaWrapper<EdjRegion> wrapper = new MPJLambdaWrapper<EdjRegion>()
                .select(EdjRegion::getId, EdjRegion::getName)
                .select(EdjCity::getCityCode)
                .innerJoin(EdjCity.class, EdjCity::getId, EdjRegion::getEdjCityId)
                .eq(EdjRegion::getActiveStatus, EdjRegionActiveStatus.ENABLED)
                .orderByAsc(EdjRegion::getSortNum);

        return regionMapper.selectJoinList(RegionSimpleVO.class, wrapper);
    }

    @Override
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
}