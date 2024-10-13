package com.edj.foundations.mapper;

import com.edj.foundations.domain.entity.EdjServeType;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_serve_type(服务类型表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/10/12
 */
@Mapper
public interface EdjServeTypeMapper extends MPJBaseMapper<EdjServeType> {
}