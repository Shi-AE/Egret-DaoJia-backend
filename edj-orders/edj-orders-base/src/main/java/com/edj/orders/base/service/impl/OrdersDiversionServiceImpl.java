package com.edj.orders.base.service.impl;


import cn.hutool.extra.spring.SpringUtil;
import com.edj.api.api.foundations.ConfigRegionApi;
import com.edj.api.api.foundations.dto.ConfigRegionInnerDTO;
import com.edj.common.utils.DateUtils;
import com.edj.orders.base.domain.entity.EdjOrders;
import com.edj.orders.base.domain.entity.EdjOrdersDispatch;
import com.edj.orders.base.domain.entity.EdjOrdersGrab;
import com.edj.orders.base.mapper.EdjOrdersDispatchMapper;
import com.edj.orders.base.mapper.EdjOrdersGrabMapper;
import com.edj.orders.base.mapper.EdjOrdersMapper;
import com.edj.orders.base.service.OrdersDiversionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 订单分流服务实现
 *
 * @author A.E.
 * @date 2025/4/22
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrdersDiversionServiceImpl implements OrdersDiversionService {

    private final EdjOrdersMapper ordersMapper;

    private final ConfigRegionApi configRegionApi;

    private final EdjOrdersGrabMapper ordersGrabMapper;

    private final EdjOrdersDispatchMapper ordersDispatchMapper;

    @Override
    public void diversion(Long ordersId) {
        EdjOrders orders = ordersMapper.selectById(ordersId);

        // 判断订单服务时间
        LocalDateTime serveStartTime = orders.getServeStartTime();
        LocalDateTime now = LocalDateTime.now();
        java.lang.Long id = orders.getId();
        if (serveStartTime.isBefore(now)) {
            // 订单已超过服务开始时间
            log.debug("订单 {} 已超过服务开始时间, 不进入发放订单流程", id);
        }

        // 查询区域订单配置
        String cityCode = orders.getCityCode();
        ConfigRegionInnerDTO configRegion = configRegionApi.findByCityCode(cityCode);

        // 储存分流数据
        SpringUtil.getBean(this.getClass()).diversionCommit(configRegion, orders);
    }

    @Transactional
    public void diversionCommit(ConfigRegionInnerDTO configRegion, EdjOrders orders) {
        // 分流间隔
        Integer diversionInterval = configRegion.getDiversionInterval();

        // 当前时间与服务开始时间的间隔
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime serveStartTime = orders.getServeStartTime();
        Duration between = DateUtils.between(now, serveStartTime);

        // 计算排序字段
        Long ordersId = orders.getId();
        int sortBy = Math.toIntExact((DateUtils.toEpochMilli(serveStartTime) + ordersId) % 100000);

        Long edjServeTypeId = orders.getEdjServeTypeId();
        Long edjServeItemId = orders.getEdjServeItemId();
        String serveTypeName = orders.getServeTypeName();
        String serveItemName = orders.getServeItemName();
        String cityCode = orders.getCityCode();
        String serveAddress = orders.getServeAddress();
        String serveItemImg = orders.getServeItemImg();
        BigDecimal realPayAmount = orders.getRealPayAmount();
        LocalDateTime payTime = orders.getPayTime();
        BigDecimal lon = orders.getLon();
        BigDecimal lat = orders.getLat();
        Integer purNum = orders.getPurNum();
        long minutes = between.toMinutes();

        EdjOrdersGrab ordersGrab = EdjOrdersGrab
                .builder()
                .id(ordersId)
                .edjServeTypeId(edjServeTypeId)
                .edjServeItemId(edjServeItemId)
                .serveTypeName(serveTypeName)
                .serveItemName(serveItemName)
                .cityCode(cityCode)
                .serveAddress(serveAddress)
                .serveItemImg(serveItemImg)
                .ordersAmount(realPayAmount)
                .serveStartTime(serveStartTime)
                .paySuccessTime(payTime)
                .lon(lon)
                .lat(lat)
                .sortBy(sortBy)
                .purNum(purNum)
                .isTimeOut(minutes < diversionInterval)
                .build();
        ordersGrabMapper.insert(ordersGrab);

        // 当时间间隔小于指定间隔插入派单表
        if (minutes < diversionInterval) {
            EdjOrdersDispatch ordersDispatch = EdjOrdersDispatch
                    .builder()
                    .id(ordersId)
                    .edjServeTypeId(edjServeTypeId)
                    .edjServeItemId(edjServeItemId)
                    .serveTypeName(serveTypeName)
                    .serveItemName(serveItemName)
                    .cityCode(cityCode)
                    .serveAddress(serveAddress)
                    .serveItemImg(serveItemImg)
                    .ordersAmount(realPayAmount)
                    .serveStartTime(serveStartTime)
                    .paySuccessTime(payTime)
                    .lon(lon)
                    .lat(lat)
                    .sortBy(sortBy)
                    .purNum(purNum)
                    .build();
            ordersDispatchMapper.insert(ordersDispatch);
        }
    }
}
