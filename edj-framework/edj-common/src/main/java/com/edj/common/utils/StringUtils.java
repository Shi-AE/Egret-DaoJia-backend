package com.edj.common.utils;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;

/**
 * 字符串工具类,继承了{@link StrUtil}
 **/
public class StringUtils extends StrUtil {
    private static final byte[] TRUE = new byte[]{'T', 'R', 'U', 'E'};

    /**
     * 判断是否是字符串
     */
    public static boolean isStr(byte[] bytes) {
        if (Arrays.equals(TRUE, bytes)) {
            return false;
        }
        for (byte word : bytes) {
            //判断是数字
            if (word >= 48 && word <= 57) {
                continue;
            }
            return true;
        }
        return false;
    }
}