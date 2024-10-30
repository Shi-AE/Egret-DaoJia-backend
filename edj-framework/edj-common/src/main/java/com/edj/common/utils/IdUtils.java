package com.edj.common.utils;

import cn.hutool.core.util.IdUtil;

/**
 * @author A.E.
 * @date 2024/9/19
 */
public class IdUtils extends IdUtil {

    public static final String ID = "id";

    public static final String EDJ = "EDJ";

    public static final String wechat = "wx";

    public static final long DEFAULT_TWEPOCH = 1728745117883L;

    public static final long DEFAULT_TIME_OFFSET = 2000L;

    public static final boolean IS_USE_SYSTEM_CLOCK = false;

    public static final long RANDOM_SEQUENCE_LIMIT = 0L;

    public static long getDataCenterId(long id) {
        return id >> 17 & 0x1FL;
    }

    public static long getWorkerId(long id) {
        return id >> 12 & 0x1FL;
    }

    public static long getGenerateDateTime(long id) {
        return (id >> 22 & 0x1FFFFFFFFFFL) + DEFAULT_TWEPOCH;
    }

    public static long getSequence(long id) {
        return id & 0xFFFL;
    }

    /**
     * 雪花id转为可视编号
     */
    public static String toCode(long id) {
        long generateDateTime = IdUtils.getGenerateDateTime(id);
        long dataCenterId = IdUtils.getDataCenterId(id);
        long workerId = IdUtils.getWorkerId(id);
        long sequence = IdUtils.getSequence(id);
        return String.format("%s_%s_%2d%2d%04d",
                EDJ,
                DateUtils.getFormatLong(generateDateTime),
                dataCenterId,
                workerId,
                sequence
        );
    }

    /**
     * 雪花id转微信用户名
     */
    public static String toWechatUserName(long id) {
        return String.format("%s_%s_%s", EDJ, wechat, id);
    }
}
