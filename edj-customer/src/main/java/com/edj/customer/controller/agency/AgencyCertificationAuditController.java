package com.edj.customer.controller.agency;

import com.edj.customer.domain.dto.AgencyCertificationAuditApplyDTO;
import com.edj.customer.domain.vo.RejectReasonVO;
import com.edj.customer.service.EdjAgencyCertificationAuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 机构端 - 服务人员认证审核相关接口
 *
 * @author A.E.
 * @date 2024/11/20
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("agency/certification/audit")
@Tag(name = "机构端 - 服务人员认证审核相关接口")
public class AgencyCertificationAuditController {

    private final EdjAgencyCertificationAuditService agencyCertificationAuditService;

    /**
     * 机构端提交认证申请
     */
    @PostMapping
    @Operation(summary = "机构端提交认证申请")
    @PreAuthorize("hasAuthority('agency:certification:apply')")
    public void apply(@RequestBody @Validated AgencyCertificationAuditApplyDTO agencyCertificationAuditApplyDTO) {
        agencyCertificationAuditService.applyCertification(agencyCertificationAuditApplyDTO);
    }

    /**
     * 查询最新的驳回原因
     */
    @GetMapping("reject/reason")
    @Operation(summary = "机构端查询最新的驳回原因")
    @PreAuthorize("hasAuthority('agency:certification:reason')")
    public RejectReasonVO queryCurrentUserLastRejectReason() {
        return agencyCertificationAuditService.getLastRejectReason();
    }
}
