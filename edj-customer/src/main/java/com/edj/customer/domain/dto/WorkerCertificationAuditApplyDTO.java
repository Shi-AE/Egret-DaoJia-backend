package com.edj.customer.domain.dto;

import com.edj.mvc.annotation.citizen.id.CitizenId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务人员提交认证申请
 *
 * @author A.E.
 * @date 2024/11/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务人员提交认证申请")
public class WorkerCertificationAuditApplyDTO {

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    @Schema(description = "姓名")
    private String name;

    /**
     * 身份证号
     */
    @NotBlank
    @CitizenId
    @Schema(description = "身份证号")
    private String idCardNo;

    /**
     * 身份证正面
     */
    @NotBlank(message = "身份证正面不能为空")
    @Schema(description = "身份证正面")
    private String frontImg;

    /**
     * 身份证反面
     */
    @NotBlank(message = "身份证反面不能为空")
    @Schema(description = "身份证反面")
    private String backImg;

    /**
     * 证明资料
     */
    @NotBlank(message = "证明资料不能为空")
    @Schema(description = "证明资料")
    private String certificationMaterial;
}
