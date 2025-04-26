package com.edj.common.constants;

public class CommonRedisConstants {

    public static class Generator {
        /**
         * 订单id生成键
         */
        public static final String ORDERS_KEY_ID_GENERATOR = "GENERATOR:ORDERS:KEY";
    }

    public static class Lock {
        /**
         * 网关 token 更新锁
         */
        public static final String GATEWAY_REFRESH_TOKEN = "EDJ_LOCK:GATEWAY:REFRESH:TOKEN:%s";

        /**
         * 交易创建锁
         */
        public static final String TRADE_CREATE = "EDJ_LOCK:TRADE:CREATE:%d";

        /**
         * 交易关闭锁
         */
        public static final String TRADE_CLOSE = "EDJ_LOCK:TRADE:CLOSE:%d";

        /**
         * 交易查询
         */
        public static final String TRADE_QUERY = "EDJ_LOCK:TRADE:QUERY:%d";

        /**
         * 交易退款
         */
        public static final String TRADE_REFUND = "EDJ_LOCK:TRADE:REFUND:%d";

        /**
         * 交易退款查询
         */
        public static final String TRADE_REFUND_QUERY = "EDJ_LOCK:TRADE:REFUND:QUERY:%d";

        /**
         * 同步抢券结果锁
         */
        public static final String GRAB_COUPON_LOCK = "EDJ_LOCK:GRAB:COUPON:%s";

        /**
         * 同步抢单结果锁
         */
        public static final String GRAB_ORDERS_LOCK = "EDJ_LOCK:GRAB:ORDERS:%s";
    }
}
