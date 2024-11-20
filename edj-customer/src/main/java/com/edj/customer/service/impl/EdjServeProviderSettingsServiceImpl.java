package com.edj.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.*;
import com.edj.customer.domain.dto.ServeScopeSetDTO;
import com.edj.customer.domain.entity.*;
import com.edj.customer.domain.vo.ServeProviderSettingsVO;
import com.edj.customer.domain.vo.ServeSettingsStatusVo;
import com.edj.customer.enums.EdjCertificationStatus;
import com.edj.customer.enums.EdjServeProviderSettingsCanPickUp;
import com.edj.customer.enums.EdjServeProviderSettingsHaveSkill;
import com.edj.customer.enums.EdjSettingStatus;
import com.edj.customer.mapper.*;
import com.edj.customer.service.EdjServeProviderSettingsService;
import com.edj.security.enums.EdjSysRole;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * 针对表【edj_serve_provider_settings(服务人员/机构认证信息)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EdjServeProviderSettingsServiceImpl extends MPJBaseServiceImpl<EdjServeProviderSettingsMapper, EdjServeProviderSettings> implements EdjServeProviderSettingsService {

    private final EdjServeProviderMapper serveProviderMapper;

    private final EdjServeProviderSyncMapper serveProviderSyncMapper;

    private final EdjWorkerCertificationMapper workerCertificationMapper;

    private final EdjAgencyCertificationMapper agencyCertificationMapper;

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

    @Override
    public ServeProviderSettingsVO getServeScope() {
        Long userId = SecurityUtils.getUserId();
        EdjServeProviderSettings serveProviderSettings = baseMapper.selectById(userId);

        if (ObjectUtils.isNull(serveProviderSettings)) {
            log.warn("未知用户设置服务范围 userId: {}", userId);
            baseMapper.insertOrUpdate(EdjServeProviderSettings
                    .builder()
                    .id(userId)
                    .build()
            );
            serveProviderSyncMapper.insertOrUpdate(EdjServeProviderSync
                    .builder()
                    .id(userId)
                    .serveItemIdList(List.of())
                    .build()
            );
            return new ServeProviderSettingsVO();
        }

        ServeProviderSettingsVO serveProviderSettingsVO = BeanUtils.toBean(serveProviderSettings, ServeProviderSettingsVO.class);
        if (serveProviderSettings.getLon().compareTo(BigDecimal.ZERO) != 0) {
            serveProviderSettingsVO.setLocation(serveProviderSettings.getLon().toPlainString() + "," + serveProviderSettings.getLat().toPlainString());
        }

        return serveProviderSettingsVO;
    }

    @Override
    public ServeSettingsStatusVo getSettingStatus() {
        // 获取用户id
        Long userId = SecurityUtils.getUserId();

        CompletableFuture<EdjServeProviderSettings> task1 = AsyncUtils.supplyAsync(() -> {
            LambdaQueryWrapper<EdjServeProviderSettings> settingsLambdaQueryWrapper = new LambdaQueryWrapper<EdjServeProviderSettings>()
                    .select(EdjServeProviderSettings::getCanPickUp, EdjServeProviderSettings::getHaveSkill, EdjServeProviderSettings::getIntentionScope)
                    .eq(EdjServeProviderSettings::getId, userId);
            return baseMapper.selectOne(settingsLambdaQueryWrapper);
        });

        // 获取角色
        Set<Long> roles = SecurityUtils.getRoles();

        CompletableFuture<Integer> task2 = AsyncUtils.supplyAsync(() -> {
            if (roles.contains((Long) EnumUtils.value(EdjSysRole.WORKER))) {
                LambdaQueryWrapper<EdjWorkerCertification> certificationLambdaQueryWrapper = new LambdaQueryWrapper<EdjWorkerCertification>()
                        .select(EdjWorkerCertification::getCertificationStatus)
                        .eq(EdjWorkerCertification::getId, userId);
                EdjWorkerCertification certification = workerCertificationMapper.selectOne(certificationLambdaQueryWrapper);
                return ObjectUtils.defaultIfNull(certification, EdjWorkerCertification::getCertificationStatus, 0);
            } else {
                LambdaQueryWrapper<EdjAgencyCertification> certificationLambdaQueryWrapper = new LambdaQueryWrapper<EdjAgencyCertification>()
                        .select(EdjAgencyCertification::getCertificationStatus)
                        .eq(EdjAgencyCertification::getId, userId);
                EdjAgencyCertification certification = agencyCertificationMapper.selectOne(certificationLambdaQueryWrapper);
                return ObjectUtils.defaultIfNull(certification, EdjAgencyCertification::getCertificationStatus, 0);
            }
        });

        EdjServeProviderSettings serveProviderSettings = task1.join();

        // 检查存在
        if (ObjectUtils.isNull(serveProviderSettings)) {
            throw new BadRequestException("用户不存在");
        }

        // 检查状态
        ServeSettingsStatusVo serveSettingsStatusVo = new ServeSettingsStatusVo();

        // 检查接单
        boolean canPickUp = EnumUtils.eq(EdjServeProviderSettingsCanPickUp.ON, serveProviderSettings.getCanPickUp());
        serveSettingsStatusVo.setCanPickUp(canPickUp);

        // 检查技能
        boolean serveSkillHasSet = EnumUtils.eq(EdjServeProviderSettingsHaveSkill.SKILLED, serveProviderSettings.getHaveSkill());
        serveSettingsStatusVo.setServeSkillHasSet(serveSkillHasSet);

        // 检查区域设置
        boolean serveScopeHasSet = StringUtils.isNotBlank(serveProviderSettings.getIntentionScope());
        serveSettingsStatusVo.setServeScopeHasSet(serveScopeHasSet);

        // 检查认证状态
        Integer certificationStatus = task2.join();
        serveSettingsStatusVo.setCertificationStatus(certificationStatus);

        // 检查是否全部设置完成
        EdjSettingStatus settingStatus = EdjSettingStatus.NOT_COMPLETED;
        if (canPickUp &&
                serveSkillHasSet &&
                serveScopeHasSet &&
                EnumUtils.eq(EdjCertificationStatus.SUCCESS, certificationStatus)
        ) {
            settingStatus = EdjSettingStatus.COMPLETED;

            CompletableFuture<Void> update1 = AsyncUtils.runAsyncTransaction(() -> {
                // 设置到用户状态
                serveProviderMapper.updateById(EdjServeProvider
                        .builder()
                        .id(userId)
                        .settingsStatus((Integer) EnumUtils.value(EdjSettingStatus.COMPLETED))
                        .build()
                );
            });

            CompletableFuture<Void> update2 = AsyncUtils.runAsyncTransaction(() -> {
                // 同步表
                serveProviderSyncMapper.updateById(EdjServeProviderSync
                        .builder()
                        .id(userId)
                        .settingStatus((Integer) EnumUtils.value(EdjSettingStatus.COMPLETED))
                        .build()
                );
            });

            CompletableFuture.allOf(update1, update2).join();
        }

        serveSettingsStatusVo.setSettingsStatus((Integer) EnumUtils.value(settingStatus));

        return serveSettingsStatusVo;
    }
}