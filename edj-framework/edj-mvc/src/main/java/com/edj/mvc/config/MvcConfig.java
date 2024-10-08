package com.edj.mvc.config;


import com.edj.mvc.advice.CommonExceptionAdvice;
import com.edj.mvc.interceptor.UserContextInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class MvcConfig implements WebMvcConfigurer {

    /**
     * <h1>通用的ControllerAdvice异常处理器</h1>
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // token拦截器
        registry.addInterceptor(new UserContextInterceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public CommonExceptionAdvice commonExceptionAdvice() {
        return new CommonExceptionAdvice();
    }

}
