package com.edj.orders.manager.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 取消订单
 *
 * @author A.E.
 * @date 2025/3/3
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "取消订单")
public class OrdersCancelDTO {

    /**
     * 订单id
     */
    @NotNull
    @Positive
    @Schema(description = "订单id")
    private Long id;

    /**
     * 取消/退款原因
     */
    @NotBlank(message = "取消/退款原因不能为空")
    @Schema(description = "取消/退款原因")
    private String cancelReason;

    /**
     * 用户id
     */
    @JsonIgnore
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 当前用户id
     */
    @JsonIgnore
    @Schema(description = "当前用户id")
    private Long currentUserId;

    /**
     * 当前用户名称
     */
    @JsonIgnore
    @Schema(description = "当前用户名称")
    private String currentUserName;

    /**
     * 预约服务开始时间
     */
    @JsonIgnore
    @Schema(description = "预约服务开始时间")
    private LocalDateTime serveStartTime;

    /**
     * 实际支付金额
     */
    @JsonIgnore
    @Schema(description = "实际支付金额")
    private BigDecimal realPayAmount;

    /**
     * 城市编码
     */
    @JsonIgnore
    @Schema(description = "城市编码")
    private String cityCode;

    /**
     * 支付服务交易单号
     */
    @JsonIgnore
    @Schema(description = "支付服务交易单号")
    private Long tradingOrderNo;

    /**
     * 实际服务完成时间
     */
    @JsonIgnore
    @Schema(description = "实际服务完成时间")
    private LocalDateTime realServeEndTime;
}
