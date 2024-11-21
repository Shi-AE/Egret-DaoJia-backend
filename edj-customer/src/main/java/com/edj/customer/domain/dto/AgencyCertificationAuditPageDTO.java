package com.edj.customer.domain.dto;

import com.edj.common.domain.dto.PageQueryDTO;
import com.edj.customer.enums.EdjAuditStatus;
import com.edj.customer.enums.EdjCertificationStatus;
import com.edj.mvc.annotation.enums.Enums;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 服务人员分页查询
 *
 * @author A.E.
 * @date 2024/11/18
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Schema(description = "机构认证申请分页查询")
public class AgencyCertificationAuditPageDTO extends PageQueryDTO {
    /**
     * 姓名
     */
    @Schema(description = "企业名称")
    private String name;

    /**
     * 法定代表人姓名
     */
    @Schema(description = "法定代表人姓名")
    private String legalPersonName;

    /**
     * 审核状态（0未审核 1已审核）
     */
    @Enums(EdjAuditStatus.class)
    @Schema(description = "审核状态（0未审核 1已审核）")
    private Integer auditStatus;

    /**
     * 认证状态（0初始态 1认证中 2认证成功 3认证失败）
     */
    @Enums(EdjCertificationStatus.class)
    @Schema(description = "认证状态（0初始态 1认证中 2认证成功 3认证失败）")
    private Integer certificationStatus;
}
