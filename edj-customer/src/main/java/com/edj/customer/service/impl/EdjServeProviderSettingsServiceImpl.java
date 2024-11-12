package com.edj.customer.service.impl;

import com.edj.customer.domain.entity.EdjServeProviderSettings;
import com.edj.customer.domain.entity.EdjServeProviderSync;
import com.edj.customer.mapper.EdjServeProviderSettingsMapper;
import com.edj.customer.mapper.EdjServeProviderSyncMapper;
import com.edj.customer.service.EdjServeProviderSettingsService;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_serve_provider_settings(服务人员/机构认证信息)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Service
@RequiredArgsConstructor
public class EdjServeProviderSettingsServiceImpl extends MPJBaseServiceImpl<EdjServeProviderSettingsMapper, EdjServeProviderSettings> implements EdjServeProviderSettingsService {

    private final EdjServeProviderSyncMapper serveProviderSyncMapper;

    @Override
    public void setPickUp(Integer canPickUp) {
        // 获取用户id
        Long userId = SecurityUtils.getUserId();

        // 更新状态
        baseMapper.updateById(EdjServeProviderSettings
                .builder()
                .id(userId)
                .canPickUp(canPickUp)
                .build()
        );

        // 同步es
        serveProviderSyncMapper.updateById(EdjServeProviderSync
                .builder()
                .id(userId)
                .pickUp(canPickUp)
                .build()
        );
    }
}