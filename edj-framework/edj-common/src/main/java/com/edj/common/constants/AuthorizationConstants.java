package com.edj.common.constants;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * 鉴权常数
 *
 * @author A.E.
 * @date 2024/9/25
 */
public final class AuthorizationConstants {

    /**
     * header 键
     *
     * @author A.E.
     * @date 2024/9/26
     */
    public static final class HeaderKey {
        /**
         * 鉴权
         */
        public static final String AUTHORIZATION_ACCESS_TOKEN = "AuthorizationAccessToken";
        /**
         * 刷新
         */
        public static final String AUTHORIZATION_REFRESH_TOKEN = "AuthorizationRefreshToken";
    }


    /**
     * redis 键
     *
     * @author A.E.
     * @date 2024/9/25
     */
    public static final class RedisKey {
        /**
         * 鉴权token
         */
        public static final String TOKEN_KEY = "AUTHORIZATION:TOKEN:%s:%s:%s";

        public static final String REFRESH_TOKEN_KEY = "AUTHORIZATION:TOKEN:%s:%s:REFRESH:%s";

        public static final String ACCESS_TOKEN_KEY = "AUTHORIZATION:TOKEN:%s:%s:ACCESS:%s";
    }

    /**
     * 过期时间
     *
     * @author A.E.
     * @date 2024/9/25
     */
    public static final class Timeout {
        /**
         * 时间单位
         */
        public static final TemporalUnit TIMEOUT_UNIT = ChronoUnit.SECONDS;
        /**
         * 刷新过期时间
         */
        public static final Duration REFRESH_TIMEOUT = Duration.of(60 * 60 * 24, TIMEOUT_UNIT);
        /**
         * token过期时间
         */
        public static final Duration ACCESS_TIMEOUT = REFRESH_TIMEOUT.dividedBy(2);

    }
}
