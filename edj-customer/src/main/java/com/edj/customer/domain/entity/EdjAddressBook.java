package com.edj.customer.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 地址簿
 *
 * @author A.E.
 * @date 2024/11/7
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjAddressBook extends EjdBaseEntity {
    /**
     * 地址簿id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long edjUserId;

    /**
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 省份
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区 / 县
     */
    private String county;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 经度
     */
    private BigDecimal lon;

    /**
     * 维度
     */
    private BigDecimal lat;

    /**
     * 是否为默认地址（0否 1是）
     */
    private Integer isDefault;
}