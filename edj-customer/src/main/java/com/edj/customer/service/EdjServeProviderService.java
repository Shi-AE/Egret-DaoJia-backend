package com.edj.customer.service;

import com.edj.api.api.customer.dto.ProviderSettingsDetailDTO;
import com.edj.customer.domain.entity.EdjServeProvider;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_serve_provider(服务人员/机构表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/11/12
 */
public interface EdjServeProviderService extends MPJBaseService<EdjServeProvider> {

    /**
     * 查询用户设置详细信息
     */
    ProviderSettingsDetailDTO detail();
}
