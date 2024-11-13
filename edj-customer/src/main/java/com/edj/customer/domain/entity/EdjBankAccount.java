package com.edj.customer.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 银行账户
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
public class EdjBankAccount extends EjdBaseEntity {
    /**
     * 服务人员/机构id
     */
    private Long id;

    /**
     * 户名
     */
    private String name;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 网点
     */
    private String branch;

    /**
     * 银行账号
     */
    private String account;

    /**
     * 开户证明
     */
    private String accountCertification;
}