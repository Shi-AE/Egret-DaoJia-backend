package com.edj.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.ObjectUtils;
import com.edj.customer.domain.dto.WorkerCertificationAuditApplyDTO;
import com.edj.customer.domain.entity.EdjWorkerCertification;
import com.edj.customer.domain.entity.EdjWorkerCertificationAudit;
import com.edj.customer.enums.EdjCertificationStatus;
import com.edj.customer.mapper.EdjWorkerCertificationAuditMapper;
import com.edj.customer.mapper.EdjWorkerCertificationMapper;
import com.edj.customer.service.EdjWorkerCertificationAuditService;
import com.edj.security.utils.SecurityUtils;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 针对表【edj_worker_certification_audit(服务人员认证审核表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Service
@RequiredArgsConstructor
public class EdjWorkerCertificationAuditServiceImpl extends MPJBaseServiceImpl<EdjWorkerCertificationAuditMapper, EdjWorkerCertificationAudit> implements EdjWorkerCertificationAuditService {

    private final EdjWorkerCertificationMapper workerCertificationMapper;

    @Override
    @Transactional
    public void applyCertification(WorkerCertificationAuditApplyDTO workerCertificationAuditApplyDTO) {

        Long userId = SecurityUtils.getUserId();

        // 新增审核
        EdjWorkerCertificationAudit workerCertificationAudit = BeanUtils.toBean(workerCertificationAuditApplyDTO, EdjWorkerCertificationAudit.class);
        workerCertificationAudit.setEdjServeProviderId(userId);
        baseMapper.insert(workerCertificationAudit);

        // 查询认证记录
        LambdaQueryWrapper<EdjWorkerCertification> wrapper = new LambdaQueryWrapper<EdjWorkerCertification>()
                .select(EdjWorkerCertification::getCertificationStatus)
                .eq(EdjWorkerCertification::getId, userId);
        EdjWorkerCertification workerCertification = workerCertificationMapper.selectOne(wrapper);

        // 记录认证
        if (ObjectUtils.isNull(workerCertification)) {
            // 新增认证记录
            workerCertificationMapper.insert(EdjWorkerCertification
                    .builder()
                    .id(userId)
                    .certificationStatus((Integer) EnumUtils.value(EdjCertificationStatus.IN_PROGRESS))
                    .build()
            );
            return;
        }

        if (EnumUtils.ne(EdjCertificationStatus.IN_PROGRESS, workerCertification.getCertificationStatus())) {
            // 修改认证状态
            workerCertification.setId(userId);
            workerCertification.setCertificationStatus((Integer) EnumUtils.value(EdjCertificationStatus.IN_PROGRESS));
            workerCertificationMapper.updateById(workerCertification);
        }
    }
}