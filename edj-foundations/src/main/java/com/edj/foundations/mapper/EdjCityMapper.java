package com.edj.foundations.mapper;

import com.edj.foundations.domain.entity.EdjCity;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* 针对表【edj_city(城市字典)】的数据库操作Mapper
*
* @author A.E.
* @date 2024/10/16
*/
@Mapper
public interface EdjCityMapper extends MPJBaseMapper<EdjCity> {
}