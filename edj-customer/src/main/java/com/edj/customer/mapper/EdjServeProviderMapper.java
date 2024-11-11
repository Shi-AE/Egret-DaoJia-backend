package com.edj.customer.mapper;

import com.edj.customer.domain.entity.EdjServeProvider;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 针对表【edj_serve_provider(服务人员/机构表)】的数据库操作Mapper
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Mapper
public interface EdjServeProviderMapper extends MPJBaseMapper<EdjServeProvider> {
}