package com.edj.customer.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 服务人员认证审核表
 *
 * @author A.E.
 * @date 2024/11/13
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjWorkerCertificationAudit extends EjdBaseEntity {
    /**
     * 审核id
     */
    @TableId
    private Long id;

    /**
     * 服务人员id
     */
    private Long edjServeProviderId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 身份证正面
     */
    private String frontImg;

    /**
     * 身份证背面
     */
    private String backImg;

    /**
     * 证明材料
     */
    private String certificationMaterial;

    /**
     * 审核状态（0未审核 1已审核）
     */
    private Integer auditStatus;

    /**
     * 审核人id
     */
    private Long auditId;

    /**
     * 审核人姓名
     */
    private String auditName;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 认证状态（0初始态 1认证中 2认证成功 3认证失败）
     */
    private Integer certificationStatus;

    /**
     * 驳回原因
     */
    private String rejectReason;
}