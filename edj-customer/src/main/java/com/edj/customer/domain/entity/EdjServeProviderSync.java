package com.edj.customer.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.edj.common.domain.entity.EjdBaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * 服务提供者同步表
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
public class EdjServeProviderSync extends EjdBaseEntity {
    /**
     * 服务提供者id（同用户id）
     */
    private Long id;

    /**
     * 技能列表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> serveItemIdList;

    /**
     * 经度
     */
    private BigDecimal lon;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 接单
     */
    private Integer pickUp;

    /**
     * 评分，默认50分
     */
    private BigDecimal score;

    /**
     * 认证设置状态（0未完成 1已完成）
     */
    private Integer settingStatus;

    /**
     * 账号状态（0正常 1冻结）
     */
    private Integer status;
}