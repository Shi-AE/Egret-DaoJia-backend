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
        public static final String GATEWAY_REFRESH_TOKEN = "LOCK:GATEWAY:REFRESH:TOKEN:%s";

        /**
         * 交易创建锁
         */
        public static final String TRADE_CREATE = "LOCK:TRADE:CREATE:%d";

        /**
         * 交易关闭锁
         */
        public static final String TRADE_CLOSE = "LOCK:TRADE:CLOSE:%d";
    }
}
