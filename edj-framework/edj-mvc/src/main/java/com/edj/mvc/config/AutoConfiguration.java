package com.edj.mvc.config;

import com.edj.mvc.handler.RequestIdHandlerImpl;
import com.edj.mvc.handler.UserInfoHandlerImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RequestIdHandlerImpl.class, UserInfoHandlerImpl.class})
public class AutoConfiguration {
}
