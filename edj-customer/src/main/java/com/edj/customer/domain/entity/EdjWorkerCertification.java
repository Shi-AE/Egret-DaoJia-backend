package com.edj.customer.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;

import java.util.Date;

/**
 * 服务人员认证信息表
 *
 * @author A.E.
 * @date 2024/11/13
 */
public class EdjWorkerCertification extends EjdBaseEntity {
    /**
     * 服务人员认证信息id
     */
    private Long id;

    /**
     * 真实名称
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCodeNo;

    /**
     * 身份证正面
     */
    private String frontImg;

    /**
     * 身份证背面
     */
    private String backImg;

    /**
     * 证明资料
     */
    private String certificationMaterial;

    /**
     * 认证状态（0初始态 1认证中 2认证成功 3认证失败）
     */
    private Integer certificationStatus;

    /**
     * 认证时间
     */
    private Date certificationTime;
}