package com.edj.foundations.mapper;

import com.edj.foundations.domain.entity.EdjServe;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* 针对表【edj_serve(服务表)】的数据库操作Mapper
*
* @author A.E.
* @date 2024/10/23
*/
@Mapper
public interface EdjServeMapper extends MPJBaseMapper<EdjServe> {
}