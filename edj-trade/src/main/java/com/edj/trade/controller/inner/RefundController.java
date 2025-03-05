package com.edj.trade.controller.inner;

import com.edj.api.api.trade.RefundApi;
import com.edj.api.api.trade.vo.RefundResultVO;
import com.edj.common.utils.BeanUtils;
import com.edj.trade.domain.entity.EdjRefundRecord;
import com.edj.trade.service.BasicPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 内部接口 - 退款服务
 *
 * @author A.E.
 * @date 2025/3/4
 */
@Validated
@RestController
@RequestMapping("inner/refund")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 交易单服务")
public class RefundController implements RefundApi {

    private final BasicPayService basicPayService;

    /**
     * 统一收单交易退款接口
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，
     * 将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     *
     * @param tradingOrderNo 支付单号
     * @param refundAmount   退款金额
     */
    @Override
    @PostMapping("refund")
    @Operation(summary = "统一收单交易退款")
    public RefundResultVO refundTrading(
            @RequestParam @NotNull @Positive Long tradingOrderNo,
            @RequestParam @NotNull @Positive BigDecimal refundAmount
    ) {
        EdjRefundRecord refundRecord = basicPayService.refundTrading(tradingOrderNo, refundAmount);
        return BeanUtils.toBean(refundRecord, RefundResultVO.class);
    }
}
