package com.edj.mvc.filter;

import com.edj.common.domain.Result;
import com.edj.common.utils.IoUtils;
import com.edj.mvc.wrapper.ResponseWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.edj.mvc.constants.HeaderConstants.BODY_PROCESSED;


/**
 * 用于包装外网访问
 *
 * @author A.E.
 * @date 2024/9/20
 */
@Component
@Slf4j
public class PackResultFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1.无需包装，放过拦截
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if (requestURI.contains(".") ||
                requestURI.contains("/swagger") ||
                requestURI.contains("/api-docs") ||
                requestURI.contains("/inner")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 2.包装响应值
        // 2.1.处理业务，获取响应值
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ResponseWrapper responseWrapper = new ResponseWrapper(response);
        filterChain.doFilter(servletRequest, responseWrapper);

        // 无需包装
        if (response.containsHeader(BODY_PROCESSED) && response.getHeader(BODY_PROCESSED).equals("1")) {
            IoUtils.write(response.getOutputStream(), false, responseWrapper.getResponseData());
            return;
        }

        // 2.2.包装
        byte[] bytes = Result.plainOk(responseWrapper.getResponseData());
        log.debug("result : {}", new String(bytes));
        // 2.3.写入
        response.setContentType("application/json;charset=UTF-8");
        IoUtils.write(response.getOutputStream(), false, bytes);
    }
}
