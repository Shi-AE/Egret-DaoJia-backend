package com.edj.foundations.service.impl;

import com.edj.foundations.domain.entity.*;
import com.edj.foundations.domain.vo.RegionSimpleVO;
import com.edj.foundations.domain.vo.ServeCategoryVO;
import com.edj.foundations.domain.vo.ServeIconVO;
import com.edj.foundations.enums.EdjRegionActiveStatus;
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
}
