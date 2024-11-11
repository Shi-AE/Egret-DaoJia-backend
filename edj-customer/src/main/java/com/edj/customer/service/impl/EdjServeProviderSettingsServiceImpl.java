package com.edj.customer.service.impl;

import com.edj.customer.domain.entity.EdjServeProviderSettings;
import com.edj.customer.mapper.EdjServeProviderSettingsMapper;
import com.edj.customer.service.EdjServeProviderSettingsService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_serve_provider_settings(服务人员/机构认证信息)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Service
public class EdjServeProviderSettingsServiceImpl extends MPJBaseServiceImpl<EdjServeProviderSettingsMapper, EdjServeProviderSettings> implements EdjServeProviderSettingsService {
}