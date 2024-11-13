package com.edj.customer.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 机构下属服务人员
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
public class EdjInstitutionStaff extends EjdBaseEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 服务机构id
     */
    private Long institutionId;

    /**
     * 编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 证明资料列表
     */
    private Object certificationImgList;
}