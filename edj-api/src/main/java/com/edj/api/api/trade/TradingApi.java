package com.edj.api.api.trade;

import com.edj.api.api.trade.vo.TradingVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 内部接口 - 交易单服务
 *
 * @author A.E.
 * @date 2025/2/7
 */
@FeignClient(contextId = "TradingApi", value = "edj-trade", path = "inner/trading")
public interface TradingApi {

    /**
     * 根据交易单号查询交易结果
     *
     * @param tradingOrderNo 订单号
     * @return 交易单数据
     */
    @GetMapping("result")
    TradingVO getTradResultByTradingOrderNo(@RequestParam Long tradingOrderNo);
}
