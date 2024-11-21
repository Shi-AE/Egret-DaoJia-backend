package com.edj.customer.controller.operation;

import com.edj.common.domain.PageResult;
import com.edj.customer.domain.dto.AgencyCertificationAuditPageDTO;
import com.edj.customer.domain.dto.CertificationAuditDTO;
import com.edj.customer.domain.vo.AgencyCertificationAuditPageVO;
import com.edj.customer.service.EdjAgencyCertificationAuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 运营端 - 机构认证审核相关接口
 *
 * @author A.E.
 * @date 2024/11/21
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("operation/agency/certification/audit")
@Tag(name = "运营端 - 机构认证审核相关接口")
public class OperationAgencyCertificationAuditController {

    private final EdjAgencyCertificationAuditService agencyCertificationAuditService;

    /**
     * 机构认证审核信息分页查询
     */
    @PostMapping("page")
    @Operation(summary = "机构认证审核信息分页查询")
    public PageResult<AgencyCertificationAuditPageVO> page(@RequestBody @Validated AgencyCertificationAuditPageDTO agencyCertificationAuditPageDTO) {
        return agencyCertificationAuditService.page(agencyCertificationAuditPageDTO);
    }

    /**
     * 审核机构认证信息
     */
    @PutMapping("audit/{id}")
    @Operation(summary = "审核机构认证信息")
    public void auditCertification(@PathVariable("id") Long id, @ModelAttribute CertificationAuditDTO certificationAuditDTO) {
        agencyCertificationAuditService.auditCertification(id, certificationAuditDTO);
    }
}
