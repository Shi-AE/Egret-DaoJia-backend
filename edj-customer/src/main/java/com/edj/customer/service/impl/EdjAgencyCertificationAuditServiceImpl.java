package com.edj.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.customer.domain.dto.AgencyCertificationAuditApplyDTO;
import com.edj.customer.domain.entity.EdjAgencyCertification;
import com.edj.customer.domain.entity.EdjAgencyCertificationAudit;
import com.edj.customer.domain.vo.RejectReasonVO;
import com.edj.customer.enums.EdjCertificationStatus;
import com.edj.customer.mapper.EdjAgencyCertificationAuditMapper;
import com.edj.customer.mapper.EdjAgencyCertificationMapper;
import com.edj.customer.service.EdjAgencyCertificationAuditService;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 针对表【edj_agency_certification_audit(机构认证审核表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Service
@RequiredArgsConstructor
public class EdjAgencyCertificationAuditServiceImpl extends MPJBaseServiceImpl<EdjAgencyCertificationAuditMapper, EdjAgencyCertificationAudit> implements EdjAgencyCertificationAuditService {

    private final EdjAgencyCertificationAuditMapper agencyCertificationAuditMapper;

    private final EdjAgencyCertificationMapper agencyCertificationMapper;

    @Override
    @Transactional
    public void applyCertification(AgencyCertificationAuditApplyDTO agencyCertificationAuditApplyDTO) {

        // 获取用户id
        Long userId = SecurityUtils.getUserId();

        // 新增审核
        EdjAgencyCertificationAudit workerCertificationAudit = BeanUtils.toBean(agencyCertificationAuditApplyDTO, EdjAgencyCertificationAudit.class);
        workerCertificationAudit.setEdjServeProviderId(userId);
        baseMapper.insert(workerCertificationAudit);

        // 查询认证记录
        LambdaQueryWrapper<EdjAgencyCertification> wrapper = new LambdaQueryWrapper<EdjAgencyCertification>()
                .select(EdjAgencyCertification::getCertificationStatus)
                .eq(EdjAgencyCertification::getId, userId);
        EdjAgencyCertification agencyCertification = agencyCertificationMapper.selectOne(wrapper);

        // 记录认证
        if (ObjectUtils.isNull(agencyCertification)) {
            // 新增认证记录
            agencyCertificationMapper.insert(EdjAgencyCertification
                    .builder()
                    .id(userId)
                    .certificationStatus((Integer) EnumUtils.value(EdjCertificationStatus.IN_PROGRESS))
                    .build()
            );
            return;
        }

        if (EnumUtils.ne(EdjCertificationStatus.IN_PROGRESS, agencyCertification.getCertificationStatus())) {
            // 修改认证状态
            agencyCertification.setId(userId);
            agencyCertification.setCertificationStatus((Integer) EnumUtils.value(EdjCertificationStatus.IN_PROGRESS));
            agencyCertificationMapper.updateById(agencyCertification);
        }
    }

    @Override
    public RejectReasonVO getLastRejectReason() {

        // 获取用户id
        Long userId = SecurityUtils.getUserId();

        LambdaQueryWrapper<EdjAgencyCertificationAudit> wrapper = new LambdaQueryWrapper<EdjAgencyCertificationAudit>()
                .select(EdjAgencyCertificationAudit::getRejectReason)
                .eq(EdjAgencyCertificationAudit::getEdjServeProviderId, userId)
                .orderByDesc(EdjAgencyCertificationAudit::getId)
                .last("limit 1");

        EdjAgencyCertificationAudit agencyCertificationAudit = baseMapper.selectOne(wrapper);
        if (ObjectUtils.isNull(agencyCertificationAudit)) {
            return null;
        }
        return new RejectReasonVO(agencyCertificationAudit.getRejectReason());
    }
}