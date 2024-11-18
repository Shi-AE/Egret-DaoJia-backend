package com.edj.customer.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 服务人员申请认证信息
 *
 * @author A.E.
 * @date 2024/11/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "服务人员申请认证信息")
public class WorkerCertificationAuditPageVO {
    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;

    /**
     * 服务人员id
     */
    @Schema(description = "服务人员id")
    private Long serveProviderId;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    private String idCardNo;

    /**
     * 身份证正面
     */
    @Schema(description = "身份证正面")
    private String frontImg;

    /**
     * 身份证反面
     */
    @Schema(description = "身份证反面")
    private String backImg;

    /**
     * 证明资料
     */
    @Schema(description = "证明资料")
    private String certificationMaterial;

    /**
     * 审核状态（0未审核 1已审核）
     */
    @Schema(description = "审核状态（0未审核 1已审核）")
    private Integer auditStatus;

    /**
     * 审核人id
     */
    @Schema(description = "审核人id")
    private Long auditorId;

    /**
     * 审核人姓名
     */
    @Schema(description = "审核人姓名")
    private String auditorName;

    /**
     * 审核时间
     */
    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    /**
     * 认证状态（0初始态 1认证中 2认证成功 3认证失败）
     */
    @Schema(description = "认证状态（0初始态 1认证中 2认证成功 3认证失败）")
    private Integer certificationStatus;

    /**
     * 驳回原因
     */
    @Schema(description = "驳回原因")
    private String rejectReason;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
