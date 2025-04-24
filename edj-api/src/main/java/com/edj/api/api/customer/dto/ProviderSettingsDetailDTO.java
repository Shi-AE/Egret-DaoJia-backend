package com.edj.api.api.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 提供者设置详情
 *
 * @author A.E.
 * @date 2025/4/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "提供者设置详情")
public class ProviderSettingsDetailDTO {
    /**
     * 服务人员/机构id（同用户id）
     */
    private Long id;

    /**
     * 编号
     */
    private String code;

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
     * 维度
     */
    private BigDecimal lat;

    /**
     * 意向单范围
     */
    private String intentionScope;

    /**
     * 是否可以接单（-1未知 0关闭接单 1开启接单）
     */
    private Integer canPickUp;

    /**
     * 认证设置状态（0未完成 1已完成）
     */
    private Integer settingsStatus;

    /**
     * 综合评分，默认50分
     */
    private BigDecimal score;

    /**
     * 好评率
     */
    private BigDecimal goodLevelRate;

    /**
     * 服务技能服务项id列表
     */
    List<Long> serveItemIdList;
}
