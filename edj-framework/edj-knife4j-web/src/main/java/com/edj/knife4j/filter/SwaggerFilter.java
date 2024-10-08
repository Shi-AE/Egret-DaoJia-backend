package com.edj.knife4j.filter;

import cn.hutool.core.io.IoUtil;
import com.edj.knife4j.response.SwaggerTransformServletResponse;
import com.edj.knife4j.utils.ResponseWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SwaggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (((HttpServletRequest) servletRequest).getRequestURI().contains("/v3/api-docs")) {
            ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
            filterChain.doFilter(servletRequest, responseWrapper);
            responseWrapper.getResponseData();
            byte[] responseData = SwaggerTransformServletResponse.getBody(responseWrapper.getResponseData());
            IoUtil.write(servletResponse.getOutputStream(), false, responseData);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
