package com.edj.trade.job;

import cn.hutool.json.JSONUtil;
import com.edj.api.api.trade.vo.TradingVO;
import com.edj.common.constants.MqConstants;
import com.edj.common.domain.msg.TradeStatusMsg;
import com.edj.common.utils.BeanUtils;
import com.edj.common.utils.CollUtils;
import com.edj.common.utils.EnumUtils;
import com.edj.common.utils.NumberUtils;
import com.edj.rabbitmq.client.RabbitClient;
import com.edj.trade.domain.entity.EdjTrading;
import com.edj.trade.enums.EdjTradingState;
import com.edj.trade.service.BasicPayService;
import com.edj.trade.service.EdjTradingService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 交易定时任务
 *
 * @author A.E.
 * @date 2025/3/7
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TradeJob {

    private final EdjTradingService tradingService;

    private final BasicPayService basicPayService;

    private final RabbitClient rabbitClient;

    /**
     * 处理支付状态
     * 每1分钟执行一次
     */
    @XxlJob("TradingStateHandle")
    public void tradingStateHandle() {
        // 分片参数
        int shardIndex = NumberUtils.max(XxlJobHelper.getShardIndex(), 0);
        int shardTotal = NumberUtils.max(XxlJobHelper.getShardTotal(), 1);

        List<EdjTrading> tradingList = tradingService.findListByTradingState(EdjTradingState.PAYMENT_IN_PROGRESS, 100);
        if (CollUtils.isEmpty(tradingList)) {
            XxlJobHelper.log("查询到交易单列表为空 shardIndex = {}, shardTotal = {}", shardIndex, shardTotal);
            return;
        }

        List<TradeStatusMsg> tradeMsgList = tradingList.stream()
                .filter(trading -> trading.getTradingOrderNo() % shardTotal == shardIndex)
                .map(trading -> {
                    try {
                        TradingVO tradingResult = basicPayService.getTradingResult(trading.getTradingOrderNo());
                        Integer tradingState = tradingResult.getTradingState();
                        if (EnumUtils.ne(EdjTradingState.PAYMENT_IN_PROGRESS, tradingState)) {
                            TradeStatusMsg tradeStatusMsg = BeanUtils.toBean(tradingResult, TradeStatusMsg.class);
                            tradeStatusMsg.setStatusCode(tradingState);
                            tradeStatusMsg.setStatusName(EnumUtils.getEnum(tradingState, EdjTradingState.class).getDescribe());
                            tradeStatusMsg.setInfo(tradingResult.getMemo());
                            return tradeStatusMsg;
                        } else {
                            // 如果未支付，判断时间，超过20分钟未支付的订单需要关闭
                            if (tradingResult.getCreateTime().isBefore(LocalDateTime.now().minusMinutes(20))) {
                                try {
                                    basicPayService.closeTrading(trading.getTradingOrderNo());
                                } catch (Exception e) {
                                    log.error("超过20分钟未支付自动关单出现异常，交易单号: {}", trading.getTradingOrderNo());
                                }
                            }
                        }
                    } catch (Exception e) {
                        XxlJobHelper.log("查询交易单出错！shardIndex = {}, shardTotal = {}, trading = {}", shardIndex, shardTotal, trading, e);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();

        if (CollUtils.isEmpty(tradeMsgList)) {
            return;
        }

        // 发送消息通知其他系统
        String msg = JSONUtil.toJsonStr(tradeMsgList);
        rabbitClient.sendMsg(
                MqConstants.Exchanges.TRADE,
                "MqConstants.RoutingKeys.TRADE_UPDATE_STATUS",
                msg,
                null,
                null,
                false
        );
    }
}
