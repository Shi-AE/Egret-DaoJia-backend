package com.edj.customer.mapper;

import com.edj.customer.domain.entity.EdjServeProviderSync;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* 针对表【edj_serve_provider_sync(服务提供者同步表)】的数据库操作Mapper
*
* @author A.E.
* @date 2024/11/12
*/
@Mapper
public interface EdjServeProviderSyncMapper extends MPJBaseMapper<EdjServeProviderSync> {
}