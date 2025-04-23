package com.edj.api.api.foundations.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 区域业务配置
 *
 * @author A.E.
 * @date 2025/4/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "区域业务配置")
public class ConfigRegionInnerDTO {
    /**
     * 区域id
     */
    @Schema(description = "区域id")
    private Long id;

    /**
     * 城市编码
     */
    @Schema(description = "城市编码")
    private Integer edjCityId;

    /**
     * （个体）接单量限制
     */
    @Schema(description = "（个体）接单量限制")
    private Integer staffReceiveOrderMax;

    /**
     * （企业）接单量限制值
     */
    @Schema(description = "（企业）接单量限制值")
    private Integer institutionReceiveOrderMax;

    /**
     * （个体）服务范围半径
     */
    @Schema(description = "（个体）服务范围半径")
    private Integer staffServeRadius;

    /**
     * （企业）服务范围半径
     */
    @Schema(description = "（企业）服务范围半径")
    private Integer institutionServeRadius;

    /**
     * 分流间隔（单位分钟），即下单时间与服务预计开始时间的间隔
     */
    @Schema(description = "分流间隔（单位分钟），即下单时间与服务预计开始时间的间隔")
    private Integer diversionInterval;

    /**
     * 抢单超时时间间隔（单位分钟），从支付成功进入抢单后超过当前时间抢单派单同步进行
     */
    @Schema(description = "抢单超时时间间隔（单位分钟），从支付成功进入抢单后超过当前时间抢单派单同步进行")
    private Integer seizeTimeoutInterval;

    /**
     * 派单策略（1距离优先策略 2评分优先策略 3接单量优先策略）
     */
    @Schema(description = "派单策略（1距离优先策略 2评分优先策略 3接单量优先策略）")
    private Integer dispatchStrategy;

    /**
     * 派单每轮时间间隔
     */
    @Schema(description = "派单每轮时间间隔")
    private Integer dispatchPerRoundInterval;
}
