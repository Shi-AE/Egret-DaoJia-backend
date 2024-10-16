package com.edj.foundations.service.impl;

import com.edj.foundations.domain.entity.EdjCity;
import com.edj.foundations.mapper.EdjCityMapper;
import com.edj.foundations.service.EdjCityService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_city(城市字典)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/10/16
 */
@Service
public class EdjCityServiceImpl extends MPJBaseServiceImpl<EdjCityMapper, EdjCity> implements EdjCityService {
}