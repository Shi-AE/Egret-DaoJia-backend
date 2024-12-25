package com.edj.common.constants;

public class CommonRedisConstants {

    public static class RedisKey {
    }

    public static class Lock {
        /**
         * 网关 token 更新锁
         */
        public static final String GATEWAY_REFRESH_TOKEN = "LOCK:GATEWAY:REFRESH:TOKEN:%s";
    }
}
