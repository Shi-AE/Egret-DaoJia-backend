package com.edj.common.constants;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * 手机验短信常量
 *
 * @author A.E.
 * @date 2024/10/31
 */
public final class SmsConstants {

    /**
     * redis 键
     *
     * @author A.E.
     * @date 2024/10/31
     */
    public static final class RedisKey {
        /**
         * 验证码key
         * SMS:VERIFY:phone
         */
        public static final String PHONE_CODE_VERIFY_KEY = "SMS:VERIFY:%s";
    }

    /**
     * 过期时间
     *
     * @author A.E.
     * @date 2024/10/31
     */
    public static final class Timeout {
        /**
         * 时间单位
         */
        public static final TemporalUnit TIMEOUT_UNIT = ChronoUnit.SECONDS;
        /**
         * 验证码过期时间
         */
        public static final Duration PHONE_CODE_VERIFY_TIMEOUT = Duration.of(300, TIMEOUT_UNIT);

    }
}
