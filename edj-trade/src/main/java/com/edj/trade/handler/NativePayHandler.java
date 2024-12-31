package com.edj.trade.handler;

import com.edj.common.expcetions.CommonException;
import com.edj.trade.domain.entity.EdjTrading;

/**
 * Native支付方式Handler：商户生成二维码，用户扫描支付
 *
 * @author A.E.
 * @date 2024/12/31
 */
public interface NativePayHandler {


    /**
     * 统一收单线下交易预创建
     * 收银员通过收银台或商户后台调用此接口，生成二维码后，展示给用户，由用户扫描二维码完成订单支付。
     */
    void createDownLineTrading(EdjTrading tradingEntity) throws CommonException;
}
