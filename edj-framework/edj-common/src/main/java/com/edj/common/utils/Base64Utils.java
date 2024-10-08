package com.edj.common.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author A.E.
 * @date 2024/9/19
 */
public class Base64Utils {

    /**
     * 字符串编码
     *
     * @param content 内容
     * @return 编码
     */
    public static byte[] encode(String content) {
        if (null == content) {
            return "".getBytes();
        }
        return Base64.getEncoder().encode(content.getBytes(StandardCharsets.UTF_8));
    }

    public static String encodeStr(String content) {
        return new String(encode(content), StandardCharsets.UTF_8);
    }

    public static String decodeStr(String content) {
        return new String(Base64.getDecoder().decode(content.getBytes(StandardCharsets.UTF_8)));
    }


}
