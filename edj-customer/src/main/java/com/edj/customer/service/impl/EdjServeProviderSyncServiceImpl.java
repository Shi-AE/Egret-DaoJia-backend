package com.edj.customer.service.impl;

import com.edj.customer.domain.entity.EdjServeProviderSync;
import com.edj.customer.mapper.EdjServeProviderSyncMapper;
import com.edj.customer.service.EdjServeProviderSyncService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_serve_provider_sync(服务提供者同步表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Service
public class EdjServeProviderSyncServiceImpl extends MPJBaseServiceImpl<EdjServeProviderSyncMapper, EdjServeProviderSync> implements EdjServeProviderSyncService {
}