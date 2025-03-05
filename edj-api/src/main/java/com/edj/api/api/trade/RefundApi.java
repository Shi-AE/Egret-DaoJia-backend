package com.edj.api.api.trade;

import com.edj.api.api.trade.vo.RefundResultVO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 内部接口 - 退款服务
 *
 * @author A.E.
 * @date 2025/3/4
 */
@FeignClient(contextId = "RefundApi", value = "edj-trade", path = "inner/refund")
public interface RefundApi {

    /***
     * 统一收单交易退款接口
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，
     * 将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     * @param tradingOrderNo 支付单号
     * @param refundAmount 退款金额
     */
    @PostMapping("refund")
    RefundResultVO refundTrading(
            @RequestParam @NotNull @Positive Long tradingOrderNo,
            @RequestParam @NotNull @Positive BigDecimal refundAmount
    );
}
