package com.edj.customer.service.impl;

import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.BeanUtils;
import com.edj.customer.domain.dto.ServeScopeSetDTO;
import com.edj.customer.domain.entity.EdjServeProviderSettings;
import com.edj.customer.domain.entity.EdjServeProviderSync;
import com.edj.customer.mapper.EdjServeProviderSettingsMapper;
import com.edj.customer.mapper.EdjServeProviderSyncMapper;
import com.edj.customer.service.EdjServeProviderSettingsService;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
    @Transactional
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

    @Override
    @Transactional
    public void setServeScope(ServeScopeSetDTO serveScopeSetDTO) {

        EdjServeProviderSettings serveProviderSettings = BeanUtils.toBean(serveScopeSetDTO, EdjServeProviderSettings.class);

        String location = serveScopeSetDTO.getLocation();
        String[] locationSplit = location.split(",");
        if (locationSplit.length != 2) {
            throw new BadRequestException("坐标格式错误");
        }
        // 经度
        BigDecimal lon = new BigDecimal(locationSplit[0]);
        serveProviderSettings.setLon(lon);
        // 纬度
        BigDecimal lat = new BigDecimal(locationSplit[1]);
        serveProviderSettings.setLat(lat);

        Long userId = SecurityUtils.getUserId();
        serveProviderSettings.setId(userId);

        baseMapper.updateById(serveProviderSettings);

        serveProviderSyncMapper.updateById(EdjServeProviderSync
                .builder()
                .id(userId)
                .lon(lon)
                .lat(lat)
                .cityCode(serveScopeSetDTO.getCityCode())
                .build()
        );
    }
}