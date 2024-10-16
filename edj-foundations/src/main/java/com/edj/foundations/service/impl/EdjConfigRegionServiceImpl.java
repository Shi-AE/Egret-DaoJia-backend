package com.edj.foundations.service.impl;

import com.edj.foundations.domain.entity.EdjConfigRegion;
import com.edj.foundations.mapper.EdjConfigRegionMapper;
import com.edj.foundations.service.EdjConfigRegionService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 针对表【edj_config_region(区域业务配置)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/16
 */
@Service
public class EdjConfigRegionServiceImpl extends MPJBaseServiceImpl<EdjConfigRegionMapper, EdjConfigRegion> implements EdjConfigRegionService {

    @Override
    @Transactional
    public void init(Long id, Integer edjCityId) {
        EdjConfigRegion configRegion = EdjConfigRegion
                .builder()
                .id(id)
                .edjCityId(edjCityId)
                // 个人接单数量限制，默认10个
                .staffReceiveOrderMax(10)
                // 机构接单数量限制，默认100个
                .institutionReceiveOrderMax(100)
                // 个人接单范围半径 50公里
                .staffServeRadius(50)
                // 机构接单范围半径200公里
                .institutionServeRadius(200)
                // 分流时间间隔120分钟，即下单时间与服务预计开始时间的间隔
                .diversionInterval(120)
                // 抢单超时时间，默认60分钟
                .seizeTimeoutInterval(60)
                // 派单策略默认距离优先策略
                .dispatchStrategy(1)
                // 派单每轮时间间隔，默认180s
                .dispatchPerRoundInterval(180)
                .build();
        baseMapper.insert(configRegion);
    }
}