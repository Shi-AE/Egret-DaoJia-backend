package com.edj.customer.domain.dto;

import com.edj.customer.enums.EdjCertificationStatus;
import com.edj.mvc.annotation.enums.Enums;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 审核服务人员认证信息
 *
 * @author A.E.
 * @date 2024/11/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "审核服务人员认证信息")
public class CertificationAuditDTO {

    /**
     * 认证状态（2认证成功 3认证失败）
     */
    @NotNull
    @Enums(EdjCertificationStatus.class)
    @Schema(description = "认证状态（2认证成功 3认证失败）")
    private Integer certificationStatus;

    /**
     * 驳回原因
     */
    @Schema(description = "驳回原因")
    private String rejectReason;
}
