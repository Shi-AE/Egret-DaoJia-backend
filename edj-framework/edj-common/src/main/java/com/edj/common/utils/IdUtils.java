package com.edj.common.utils;

import cn.hutool.core.util.IdUtil;

/**
 * @author A.E.
 * @date 2024/9/19
 */
public class IdUtils extends IdUtil {

    public static long DEFAULT_TWEPOCH = 1728745117883L;

    public static long DEFAULT_TIME_OFFSET = 2000L;

    public static boolean IS_USE_SYSTEM_CLOCK = false;

    public static long RANDOM_SEQUENCE_LIMIT = 0L;

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

    public static String toCode(long id) {
        long generateDateTime = IdUtils.getGenerateDateTime(id);
        long dataCenterId = IdUtils.getDataCenterId(id);
        long workerId = IdUtils.getWorkerId(id);
        long sequence = IdUtils.getSequence(id);
        return String.format("EDJ_%s_%d%d%d", DateUtils.getFormatLong(generateDateTime), dataCenterId, workerId, sequence);
    }
}
