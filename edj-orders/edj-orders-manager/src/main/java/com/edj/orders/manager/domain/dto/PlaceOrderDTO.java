package com.edj.orders.manager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 下单请求模型
 *
 * @author A.E.
 * @date 2024/12/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "下单请求模型")
public class PlaceOrderDTO {
    /**
     * 服务id
     */
    @Schema(description = "服务id")
    @NotNull(message = "还未选择服务")
    @Positive
    private Long serveId;

    /**
     * 预约地址id
     */
    @Schema(description = "预约地址id")
    @NotNull(message = "还未选择服务地址")
    @Positive
    private Long addressBookId;

    /**
     * 购买数量
     */
    @Schema(description = "购买数量")
    @Positive
    private Integer purNum;

    /**
     * 预约时间
     */
    @Schema(description = "预约时间")
    @NotNull(message = "服务时间不能为空")
    @Future
    private LocalDateTime serveStartTime;

    /**
     * 优惠券id
     */
    @Schema(description = "优惠券id")
    @Positive
    private Long couponId;
}
