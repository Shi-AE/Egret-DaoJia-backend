package com.edj.foundations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.foundations.domain.dto.RegionAddDTO;
import com.edj.foundations.domain.entity.EdjCity;
import com.edj.foundations.domain.entity.EdjRegion;
import com.edj.foundations.mapper.EdjCityMapper;
import com.edj.foundations.mapper.EdjRegionMapper;
import com.edj.foundations.service.EdjConfigRegionService;
import com.edj.foundations.service.EdjRegionService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 针对表【edj_region(区域表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/16
 */
@Service
@RequiredArgsConstructor
public class EdjRegionServiceImpl extends MPJBaseServiceImpl<EdjRegionMapper, EdjRegion> implements EdjRegionService {

    private final EdjCityMapper cityMapper;

    private final EdjConfigRegionService configRegionService;

    @Override
    @Transactional
    public void add(RegionAddDTO regionAddDTO) {
        // 检查城市重复
        Integer edjCityId = regionAddDTO.getEdjCityId();
        LambdaQueryWrapper<EdjRegion> checkWrapper = new LambdaQueryWrapper<EdjRegion>()
                .select(EdjRegion::getId)
                .eq(EdjRegion::getEdjCityId, edjCityId);
        boolean exists = baseMapper.exists(checkWrapper);
        if (exists) {
            throw new BadRequestException("提交重复城市");
        }

        // 检查城市
        LambdaQueryWrapper<EdjCity> cityLambdaQueryWrapper = new LambdaQueryWrapper<EdjCity>()
                .select(EdjCity::getSortNum)
                .eq(EdjCity::getId, edjCityId);
        EdjCity city = cityMapper.selectOne(cityLambdaQueryWrapper);
        // 检查存在
        if (ObjectUtils.isNull(city)) {
            throw new BadRequestException("城市不存在");
        }
        // 获取城市排序位
        Integer sortNum = city.getSortNum();

        // 新增区域
        EdjRegion region = BeanUtils.toBean(regionAddDTO, EdjRegion.class);
        region.setSortNum(sortNum);
        baseMapper.insert(region);

        // 初始化区域配置
        Long id = region.getId();
        configRegionService.init(id, edjCityId);
    }
}