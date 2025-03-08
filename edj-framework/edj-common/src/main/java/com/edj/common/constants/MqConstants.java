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
     * 队列类型参数名 x-queue-type
     */
    public static final String X_QUEUE_TYPE = "x-queue-type";

    /**
     * 队列类型参数值 quorum
     */
    public static final String QUORUM = "quorum";

    /**
     * 定义消息交换机，约定：1：类型都为topic，2：延迟队列命名由.delayed结尾
     */
    public static class Exchanges {

        /**
         * canal
         */
        public static final String CANAL = "edj.exchange.topic.canal";

        /**
         * 交易（支付）
         */
        public static final String TRADE = "edj.exchange.topic.trade";

        /**
         * 评分
         */
        public static final String EVALUATION_SCORE = "edj.exchange.topic.evaluation.score";
    }

    /**
     * 定义消息队列
     */
    public static class Queues {

        /**
         * 基础服务：canal 同步edj_serve_sync表数据至 es
         */
        public static final String FOUNDATION_SYNC_TO_ES = "edj.queue.foundation.serve.sync.to.es";

        /**
         * 订单微服务：更新支付状态
         */
        public static final String ORDERS_TRADE_UPDATE_STATUS = "edj.queue.orders.trade.update.status";

        /**
         * 订单微服务：更新评分
         */
        public static final String CUSTOMER_SCORE_STATISTICS = "edj.queue.customer.score.statistics";
    }

    /**
     * 定义路由key
     */
    public static class RoutingKeys {

        /**
         * canal 同步edj_serve_sync表数据至 es
         */
        public static final String SERVE_SYNC_TO_ES = "edj-key-serve-sync-to-es";

        /**
         * 更新支付状态
         */
        public static final String UPDATE_STATUS = "edj.key.update.status";

        /**
         * 分数统计
         */
        public static final String UPDATE_SCORE_STATISTICS = "edj.key.update.score.statistics";
    }
}
