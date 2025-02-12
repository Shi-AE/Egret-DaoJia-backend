package com.edj.trade.controller.inner;

import com.edj.api.api.trade.TradingApi;
import com.edj.api.api.trade.vo.TradingVO;
import com.edj.trade.service.BasicPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内部接口 - 交易单服务
 *
 * @author A.E.
 * @date 2025/2/7
 */
@Validated
@RestController
@RequestMapping("inner/trading")
@RequiredArgsConstructor
@Tag(name = "内部接口 - 交易单服务")
public class TradingController implements TradingApi {

    private final BasicPayService basicPayService;

    /**
     * 根据交易单号查询交易结果
     *
     * @param tradingOrderNo 订单号
     * @return 交易单数据
     */
    @Override
    @GetMapping("result")
    @Operation(summary = "根据交易单号查询交易结果")
    public TradingVO getTradResultByTradingOrderNo(@RequestParam Long tradingOrderNo) {
        return basicPayService.getTradingResult(tradingOrderNo);
    }
}
