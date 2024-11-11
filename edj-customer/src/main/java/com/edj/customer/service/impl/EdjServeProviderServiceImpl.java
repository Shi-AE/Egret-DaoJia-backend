package com.edj.customer.service.impl;

import com.edj.customer.domain.entity.EdjServeProvider;
import com.edj.customer.mapper.EdjServeProviderMapper;
import com.edj.customer.service.EdjServeProviderService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_serve_provider(服务人员/机构表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Service
public class EdjServeProviderServiceImpl extends MPJBaseServiceImpl<EdjServeProviderMapper, EdjServeProvider> implements EdjServeProviderService {
}