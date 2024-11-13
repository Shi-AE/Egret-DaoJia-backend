package com.edj.customer.service.impl;

import com.edj.customer.domain.entity.EdjAgencyCertificationAudit;
import com.edj.customer.mapper.EdjAgencyCertificationAuditMapper;
import com.edj.customer.service.EdjAgencyCertificationAuditService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 针对表【edj_agency_certification_audit(机构认证审核表)】的数据库操作Service实现
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Service
public class EdjAgencyCertificationAuditServiceImpl extends MPJBaseServiceImpl<EdjAgencyCertificationAuditMapper, EdjAgencyCertificationAudit> implements EdjAgencyCertificationAuditService {
}