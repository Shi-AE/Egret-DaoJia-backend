package com.edj.customer.service;

import com.edj.common.domain.PageResult;
import com.edj.customer.domain.dto.WorkerCertificationAuditApplyDTO;
import com.edj.customer.domain.dto.WorkerCertificationAuditPageDTO;
import com.edj.customer.domain.entity.EdjWorkerCertificationAudit;
import com.edj.customer.domain.vo.WorkerCertificationAuditPageVO;
import com.github.yulichang.base.MPJBaseService;

/**
 * 针对表【edj_worker_certification_audit(服务人员认证审核表)】的数据库操作Service
 *
 * @author A.E.
 * @date 2024/11/13
 */
public interface EdjWorkerCertificationAuditService extends MPJBaseService<EdjWorkerCertificationAudit> {

    /**
     * 服务人员提交认证申请
     */
    void applyCertification(WorkerCertificationAuditApplyDTO workerCertificationAuditApplyDTO);

    /**
     * 服务人员认证审核信息分页查询
     */
    PageResult<WorkerCertificationAuditPageVO> page(WorkerCertificationAuditPageDTO workerCertificationAuditPageDTO);
}
