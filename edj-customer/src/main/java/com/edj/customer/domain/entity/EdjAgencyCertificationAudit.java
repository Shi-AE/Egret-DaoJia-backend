package com.edj.customer.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 机构认证审核表
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
public class EdjAgencyCertificationAudit extends EjdBaseEntity {
    /**
     * 机构id
     */
    private Long id;

    /**
     * 机构id
     */
    private Long edjServeProviderId;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 统一社会信用代码
     */
    private String idNumber;

    /**
     * 法定代表人姓名
     */
    private String legalPersonName;

    /**
     * 法定代表人身份证号
     */
    private String legalPersonIdCardNo;

    /**
     * 营业执照
     */
    private String businessLicense;

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
    private LocalDateTime auditTime;

    /**
     * 驳回原因
     */
    private String rejectReason;

    /**
     * 认证状态（0初始态 1认证中 2认证成功 3认证失败）
     */
    private Integer certificationStatus;
}