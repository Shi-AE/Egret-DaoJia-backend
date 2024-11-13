package com.edj.customer.service.impl;

import com.edj.customer.domain.entity.EdjWorkerCertificationAudit;
import com.edj.customer.mapper.EdjWorkerCertificationAuditMapper;
import com.edj.customer.service.EdjWorkerCertificationAuditService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_worker_certification_audit(服务人员认证审核表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Service
public class EdjWorkerCertificationAuditServiceImpl extends MPJBaseServiceImpl<EdjWorkerCertificationAuditMapper, EdjWorkerCertificationAudit> implements EdjWorkerCertificationAuditService {
}