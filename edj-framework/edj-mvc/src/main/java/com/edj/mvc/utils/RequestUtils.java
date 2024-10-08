package com.edj.mvc.utils;


import com.edj.common.utils.CollUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * 请求工具
 *
 * @author A.E.
 * @date 2024/10/8
 */
public class RequestUtils {

    /**
     * 获取当前线程请求对象
     *
     * @return 请求对象
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = ServletRequestAttributesUtils.getServletRequestAttributes();
        return servletRequestAttributes == null ? null : servletRequestAttributes.getRequest();
    }

    /**
     * 从header头中获取一个header值
     *
     * @param headerKey header头
     * @return header值
     */
    public static String getValueFromHeader(String headerKey) {
        HttpServletRequest request = getRequest();
        return request == null ? null : request.getHeader(headerKey);
    }

    /**
     * 从header头像获取一个header的多个值列表
     *
     * @param headerKey
     * @return
     */
    public static List<String> getValuesFromHeader(String headerKey) {
        HttpServletRequest request = getRequest();
        return request == null ? CollUtils.emptyList() : CollUtils.toList(request.getHeaders(headerKey));
    }
}
