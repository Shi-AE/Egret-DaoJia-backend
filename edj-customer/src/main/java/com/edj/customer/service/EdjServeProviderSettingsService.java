package com.edj.customer.service;

import com.edj.customer.domain.dto.ServeScopeSetDTO;
import com.edj.customer.domain.entity.EdjServeProviderSettings;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_serve_provider_settings(服务人员/机构认证信息)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/11/12
 */
public interface EdjServeProviderSettingsService extends MPJBaseService<EdjServeProviderSettings> {

    /**
     * 接单设置
     */
    void setPickUp(Integer canPickUp);

    /**
     * 服务范围设置
     */
    void setServeScope(ServeScopeSetDTO serveScopeSetDTO);
}
