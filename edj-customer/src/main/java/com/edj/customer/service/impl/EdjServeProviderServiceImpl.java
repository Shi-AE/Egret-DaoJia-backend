package com.edj.customer.service.impl;

import com.edj.api.api.customer.dto.ProviderSettingsDetailDTO;
import com.edj.customer.domain.entity.EdjServeProvider;
import com.edj.customer.domain.entity.EdjServeProviderSettings;
import com.edj.customer.domain.entity.EdjServeSkill;
import com.edj.customer.mapper.EdjServeProviderMapper;
import com.edj.customer.service.EdjServeProviderService;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_serve_provider(服务人员/机构表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Service
public class EdjServeProviderServiceImpl extends MPJBaseServiceImpl<EdjServeProviderMapper, EdjServeProvider> implements EdjServeProviderService {
    @Override
    public ProviderSettingsDetailDTO detail() {
        Long userId = SecurityUtils.getUserId();
        MPJLambdaWrapper<EdjServeProvider> wrapper = new MPJLambdaWrapper<EdjServeProvider>()
                .select(EdjServeProvider::getId, EdjServeProvider::getCode, EdjServeProvider::getSettingsStatus,
                        EdjServeProvider::getScore, EdjServeProvider::getGoodLevelRate)
                .select(EdjServeProviderSettings::getCityCode, EdjServeProviderSettings::getCityName,
                        EdjServeProviderSettings::getLon, EdjServeProviderSettings::getLat,
                        EdjServeProviderSettings::getIntentionScope, EdjServeProviderSettings::getCanPickUp)
                .selectCollection(EdjServeSkill.class, ProviderSettingsDetailDTO::getServeItemIdList,
                        map -> map.result(EdjServeSkill::getEdjServeItemId))
                .innerJoin(EdjServeProviderSettings.class, EdjServeProviderSettings::getId, EdjServeProvider::getId)
                .innerJoin(EdjServeSkill.class, EdjServeSkill::getServeProviderId, EdjServeProvider::getId)
                .eq(EdjServeProvider::getId, userId);
        return baseMapper.selectJoinOne(ProviderSettingsDetailDTO.class, wrapper);
    }
}