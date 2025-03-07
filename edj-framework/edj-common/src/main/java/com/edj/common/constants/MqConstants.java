package com.edj.common.constants;

/**
 * 静态变量
 *
 * @author A.E.
 * @date 2024/9/20
 */
public class MqConstants {
    /**
     * 默认延时时间为-1
     */
    public static final int DEFAULT_DELAY = -1;

    /**
     * 低延迟时间：5秒
     */
    public static final int LOW_DELAY = 5000;

    /**
     * 标准延迟时间：10秒
     */
    public static final int NORMAL_DELAY = 10000;

    /**
     * 延迟交换机关键字
     */
    public static final String DELAYED_KEYWORD = "delayed";

    /**
     * 表明是延迟队列
     */
    public static final String DELAYED = "true";

    /**
     * 定义消息交换机，约定：1：类型都为topic，2：延迟队列命名由.delayed结尾
     */
    public static class Exchanges {

        /**
         * 交易（支付）
         */
        public static final String TRADE = "edj.exchange.topic.trade";

        /**
         * 评分
         */
        public static final String EVALUATION_SCORE = "evaluation.score";
    }

    /**
     * 定义消息队列
     */
    public static class Queues {

        /**
         * 订单微服务：更新支付状态
         */
        public static final String ORDERS_TRADE_UPDATE_STATUS = "edj.queue.orders.trade.update.status";

        /**
         * 订单微服务：更新评分
         */
        public static final String SCORE_STATISTICS = "edj.queue.customer.score.statistics";
    }

    /**
     * 定义路由key
     */
    public static class RoutingKeys {

        /**
         * 更新支付状态
         */
        public static final String TRADE_UPDATE_STATUS = "update.status";

        /**
         * 分数统计
         */
        public static final String SCORE_STATISTICS = "evaluation.score.statistics";
    }
}
