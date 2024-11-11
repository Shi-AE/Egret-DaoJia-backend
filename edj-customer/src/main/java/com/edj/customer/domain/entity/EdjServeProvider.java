package com.edj.customer.domain.entity;

import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 服务人员/机构表
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
public class EdjServeProvider extends EjdBaseEntity {
    /**
     * 服务人员/机构表（同用户id）
     */
    private Long id;

    /**
     * 编号
     */
    private String code;

    /**
     * 认证设置状态（1未完成 2已完成）
     */
    private Integer settingsStatus;

    /**
     * 综合评分
     */
    private BigDecimal score;

    /**
     * 好评率
     */
    private BigDecimal goodLevelRate;
}