package com.edj.api.api.trade.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付渠道
 *
 * @author A.E.
 * @date 2024/12/29
 */
@Getter
@AllArgsConstructor
public enum TradingChannel {

    ALI_PAY("支付宝"),
    WECHAT_PAY("微信支付");

    @EnumValue
    private final String value;
}
