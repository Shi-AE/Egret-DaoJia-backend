package com.edj.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.common.expcetions.BadRequestException;
import com.edj.common.utils.CollUtils;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.SqlUtils;
import com.edj.customer.domain.dto.ServeSkillUpsertDTO;
import com.edj.customer.domain.entity.EdjServeProviderSettings;
import com.edj.customer.domain.entity.EdjServeProviderSync;
import com.edj.customer.domain.entity.EdjServeSkill;
import com.edj.customer.enums.EdjServeProviderSettingsHaveSkill;
import com.edj.customer.mapper.EdjServeProviderSettingsMapper;
import com.edj.customer.mapper.EdjServeProviderSyncMapper;
import com.edj.customer.mapper.EdjServeSkillMapper;
import com.edj.customer.service.EdjServeSkillService;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 针对表【edj_serve_skill(服务技能表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Service
@AllArgsConstructor
public class EdjServeSkillServiceImpl extends MPJBaseServiceImpl<EdjServeSkillMapper, EdjServeSkill> implements EdjServeSkillService {

    private final EdjServeProviderSyncMapper serveProviderSyncMapper;

    private final EdjServeProviderSettingsMapper serveProviderSettingsMapper;

    @Override
    @Transactional
    public void batchUpsert(List<ServeSkillUpsertDTO> serveSkillUpsertDTOList) {
        // 获取用户id
        Long userId = SecurityUtils.getUserId();

        // 删除已有技能
        LambdaQueryWrapper<EdjServeSkill> deleteWrapper = new LambdaQueryWrapper<EdjServeSkill>()
                .eq(EdjServeSkill::getServeProviderId, userId);
        baseMapper.delete(deleteWrapper);

        // 检查是否清空
        if (CollUtils.isEmpty(serveSkillUpsertDTOList)) {
            // 设置技能状态
            serveProviderSettingsMapper.updateById(EdjServeProviderSettings
                    .builder()
                    .id(userId)
                    .haveSkill((Integer) EnumUtils.value(EdjServeProviderSettingsHaveSkill.UNSKILLED))
                    .build()
            );

            // 同步es同步表
            serveProviderSyncMapper.updateById(EdjServeProviderSync
                    .builder()
                    .id(userId)
                    .serveItemIdList(List.of())
                    .build()
            );
            return;
        }

        // 检查服务项
        List<Long> ServeItemIdList = serveSkillUpsertDTOList
                .stream()
                .map(ServeSkillUpsertDTO::getServeItemId)
                .distinct()
                .sorted()
                .toList();

        // 检查重复
        if (ServeItemIdList.size() != serveSkillUpsertDTOList.size()) {
            throw new BadRequestException("服务项重复");
        }

        // 插入新技能
        List<EdjServeSkill> serveSkillList = serveSkillUpsertDTOList
                .stream()
                .map(x -> EdjServeSkill
                        .builder()
                        .serveProviderId(userId)
                        .edjServeTypeId(x.getServeTypeId())
                        .serveTypeName(x.getServeTypeName())
                        .edjServeItemId(x.getServeItemId())
                        .serveItemName(x.getServeItemName())
                        .build()
                )
                .collect(Collectors.toList());
        SqlUtils.actionBatch(
                serveSkillList,
                list -> baseMapper.insert(list),
                true
        );

        // 设置技能状态
        serveProviderSettingsMapper.updateById(EdjServeProviderSettings
                .builder()
                .id(userId)
                .haveSkill((Integer) EnumUtils.value(EdjServeProviderSettingsHaveSkill.SKILLED))
                .build()
        );

        // 同步es同步表
        serveProviderSyncMapper.updateById(EdjServeProviderSync
                .builder()
                .id(userId)
                .serveItemIdList(ServeItemIdList)
                .build()
        );
    }
}