package com.edj.mvc.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 响应工具
 *
 * @author A.E.
 * @date 2024/10/8
 */
public class ResponseUtils {

    /**
     * 获取response
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = ServletRequestAttributesUtils.getServletRequestAttributes();
        return servletRequestAttributes == null ? null : servletRequestAttributes.getResponse();
    }

    /**
     * 设置响应header信息
     *
     * @param key   header key
     * @param value header 值
     */
    public static void setResponseHeader(String key, String value) {
        HttpServletResponse response = getResponse();
        if (response == null) {
            return;
        }
        response.setHeader(key, value);
    }
}
