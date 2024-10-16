package com.edj.foundations.mapper;

import com.edj.foundations.domain.entity.EdjRegion;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* 针对表【edj_region(区域表)】的数据库操作Mapper
*
* @author A.E.
* @date 2024/10/16
*/
@Mapper
public interface EdjRegionMapper extends MPJBaseMapper<EdjRegion> {
}