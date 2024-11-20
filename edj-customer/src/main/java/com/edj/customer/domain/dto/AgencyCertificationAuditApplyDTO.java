package com.edj.customer.domain.dto;

import com.edj.mvc.annotation.citizen.id.CitizenId;
import com.edj.mvc.annotation.credit.code.CreditCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 机构端提交认证申请
 *
 * @author A.E.
 * @date 2024/11/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "机构端提交认证申请")
public class AgencyCertificationAuditApplyDTO {

    /**
     * 企业名称
     */
    @NotBlank(message = "企业名称不能为空")
    @Schema(description = "企业名称")
    private String name;

    /**
     * 统一社会信用代码
     */
    @CreditCode
    @NotBlank(message = "统一社会信用代码不能为空")
    @Schema(description = "统一社会信用代码")
    private String idNumber;

    /**
     * 法定代表人姓名
     */
    @NotBlank(message = "法定代表人姓名不能为空")
    @Schema(description = "法定代表人姓名")
    private String legalPersonName;

    /**
     * 法定代表人身份证号
     */
    @CitizenId
    @NotBlank(message = "法定代表人身份证号不能为空")
    @Schema(description = "法定代表人身份证号")
    private String legalPersonIdCardNo;

    /**
     * 营业执照
     */
    @NotBlank(message = "营业执照不能为空")
    @Schema(description = "营业执照")
    private String businessLicense;
}
