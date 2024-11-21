package com.edj.customer.service;

import com.edj.common.domain.PageResult;
import com.edj.customer.domain.dto.AgencyCertificationAuditApplyDTO;
import com.edj.customer.domain.dto.AgencyCertificationAuditPageDTO;
import com.edj.customer.domain.dto.CertificationAuditDTO;
import com.edj.customer.domain.entity.EdjAgencyCertificationAudit;
import com.edj.customer.domain.vo.AgencyCertificationAuditPageVO;
import com.edj.customer.domain.vo.RejectReasonVO;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_agency_certification_audit(机构认证审核表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/11/13
 */
public interface EdjAgencyCertificationAuditService extends MPJBaseService<EdjAgencyCertificationAudit> {

    /**
     * 机构端提交认证申请
     */
    void applyCertification(AgencyCertificationAuditApplyDTO agencyCertificationAuditApplyDTO);

    /**
     * 返回最新拒绝原因
     */
    RejectReasonVO getLastRejectReason();

    /**
     * 机构认证审核信息分页查询
     */
    PageResult<AgencyCertificationAuditPageVO> page(AgencyCertificationAuditPageDTO agencyCertificationAuditPageDTO);

    /**
     * 审核机构认证信息
     */
    void auditCertification(Long id, CertificationAuditDTO certificationAuditDTO);
}
