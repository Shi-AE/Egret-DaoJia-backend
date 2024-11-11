package com.edj.customer.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 服务人员/机构认证信息
 *
 * @author A.E.
 * @date 2024/11/12
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EdjServeProviderSettings extends EjdBaseEntity {
    /**
     * 服务人员/机构id（同用户id）
     */
    private Long id;

    /**
     * 城市编号
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 经度
     */
    private BigDecimal lon;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 意向单范围
     */
    private String intentionScope;

    /**
     * 是否有技能（1有 0无）
     */
    private Integer haveSkill;

    /**
     * 是否可以接单（-1未知 0关闭接单 1开启接单）
     */
    private Integer canPickUp;
}