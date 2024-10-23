package com.edj.foundations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.foundations.domain.entity.EdjCity;
import com.edj.foundations.domain.vo.ProvinceStructVO;
import com.edj.foundations.enums.EdjCityType;
import com.edj.foundations.mapper.EdjCityMapper;
import com.edj.foundations.service.EdjCityService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.edj.cache.constants.CacheConstants.CacheName.CITY_CACHE;

/**
 * 针对表【edj_city(城市字典)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/16
 */
@Service
public class EdjCityServiceImpl extends MPJBaseServiceImpl<EdjCityMapper, EdjCity> implements EdjCityService {

    @Override
    @Cacheable(cacheNames = CITY_CACHE, key = "'CityStruct'")
    public List<ProvinceStructVO> getStruct() {
        // 根据排序查询省列表
        LambdaQueryWrapper<EdjCity> pw = new LambdaQueryWrapper<EdjCity>()
                .select(EdjCity::getId, EdjCity::getName)
                .eq(EdjCity::getType, EdjCityType.PROVINCE)
                .orderByAsc(EdjCity::getSortNum);
        List<EdjCity> ProvinceCitieList = baseMapper.selectList(pw);

        // 查询市
        LambdaQueryWrapper<EdjCity> cw = new LambdaQueryWrapper<EdjCity>()
                .select(EdjCity::getId, EdjCity::getParentId, EdjCity::getName)
                .eq(EdjCity::getType, EdjCityType.CITY)
                .orderByAsc(EdjCity::getSortNum);
        List<EdjCity> cityCitieList = baseMapper.selectList(cw);

        // 构造城市 ParentId Map
        Map<Integer, List<ProvinceStructVO.CityStructVO>> cityStructList = cityCitieList
                .parallelStream()
                .collect(Collectors.groupingBy(
                        EdjCity::getParentId,
                        Collectors.mapping(
                                x -> ProvinceStructVO.CityStructVO
                                        .builder()
                                        .value(x.getId())
                                        .label(x.getName())
                                        .build(),
                                Collectors.toList()
                        )
                ));

        // 构造结构
        return ProvinceCitieList
                .parallelStream()
                .map(x -> ProvinceStructVO.builder()
                        .value(x.getId())
                        .label(x.getName())
                        .children(cityStructList.getOrDefault(x.getId(), new ArrayList<>()))
                        .build()
                )
                .toList();
    }
}