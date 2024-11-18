package com.edj.customer.controller.operation;

import com.edj.common.domain.PageResult;
import com.edj.customer.domain.dto.WorkerCertificationAuditPageDTO;
import com.edj.customer.domain.vo.WorkerCertificationAuditPageVO;
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
 * 运营端 - 服务人员认证审核相关接口
 *
 * @author A.E.
 * @date 2024/11/18
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("operation/worker/certification/audit")
@Tag(name = "运营端 - 服务人员认证审核相关接口")
public class OperationWorkerCertificationAuditController {

    private final EdjWorkerCertificationAuditService workerCertificationAuditService;

    /**
     * 服务人员认证审核信息分页查询
     */
    @PostMapping("page")
    @Operation(summary = "服务人员认证审核信息分页查询")
    public PageResult<WorkerCertificationAuditPageVO> page(@RequestBody @Validated WorkerCertificationAuditPageDTO workerCertificationAuditPageDTO) {
        return workerCertificationAuditService.page(workerCertificationAuditPageDTO);
    }
}
