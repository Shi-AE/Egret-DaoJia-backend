package com.edj.orders.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan({"com.edj.orders.base.service", "com.edj.orders.base.handler"})
@ComponentScan("com.edj.orders.base.service")
@MapperScan("com.edj.orders.base.mapper")
public class AutoImportConfiguration {
}
