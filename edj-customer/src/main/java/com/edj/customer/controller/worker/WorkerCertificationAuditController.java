package com.edj.customer.controller.worker;

import com.edj.customer.domain.dto.WorkerCertificationAuditApplyDTO;
import com.edj.customer.service.EdjWorkerCertificationAuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务端 - 服务人员认证审核相关接口
 *
 * @author A.E.
 * @date 2024/11/15
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("worker/certification/audit")
@Tag(name = "服务端 - 服务人员认证审核相关接口")
public class WorkerCertificationAuditController {

    private final EdjWorkerCertificationAuditService workerCertificationAuditService;

    /**
     * 服务人员提交认证申请
     */
    @PostMapping
    @Operation(summary = "服务人员提交认证申请")
    public void auditCertification(@RequestBody @Validated WorkerCertificationAuditApplyDTO workerCertificationAuditApplyDTO) {
        workerCertificationAuditService.applyCertification(workerCertificationAuditApplyDTO);
    }
}
