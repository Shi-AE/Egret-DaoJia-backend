package com.edj.orders.manager.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下单返回信息
 *
 * @author A.E.
 * @date 2024/12/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "下单返回信息")
public class PlaceOrderVO {
    /**
     * 订单id
     */
    @Schema(description = "订单id")
    private Long id;
}
