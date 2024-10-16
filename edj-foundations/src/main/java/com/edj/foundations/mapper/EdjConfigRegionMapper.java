package com.edj.foundations.mapper;

import com.edj.foundations.domain.entity.EdjConfigRegion;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* 针对表【edj_config_region(区域业务配置)】的数据库操作Mapper
*
* @author A.E.
* @date 2024/10/16
*/
@Mapper
public interface EdjConfigRegionMapper extends MPJBaseMapper<EdjConfigRegion> {
}