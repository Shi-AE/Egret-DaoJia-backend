package com.edj.orders.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 *
 * @author A.E.
 * @date 2024/12/26
 */
@Getter
@AllArgsConstructor
public enum EdjOrderStatus {

    PENDING_PAYMENT(0, "待支付"),
    DISPATCHING(100, "派单中"),
    PENDING_SERVE(200, "待服务"),
    IN_SERVE(300, "服务中"),
    COMPLETED(500, "订单完成"),
    CANCELLED(600, "已取消"),
    CLOSED(700, "已关闭");

    @EnumValue
    private final Integer value;
    private final String describe;
}

