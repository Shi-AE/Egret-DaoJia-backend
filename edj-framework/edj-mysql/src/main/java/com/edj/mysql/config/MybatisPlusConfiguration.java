package com.edj.mysql.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.edj.mysql.interceptor.MyBatisAutoFillInterceptor;
import com.edj.mysql.properties.MybatisPlusProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MybatisPlusProperties.class)
public class MybatisPlusConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor(@Autowired(required = false) DynamicTableNameInnerInterceptor innerInterceptor, MybatisPlusProperties mybatisPlusProperties) {
        // 1.定义插件主体，注意顺序：表名 > 多租户 > 分页 > 乐观锁 > 字段填充
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 2.表名插件
        if (innerInterceptor != null) {
            interceptor.addInnerInterceptor(innerInterceptor);
        }

        // 3.分页插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        paginationInnerInterceptor.setMaxLimit(mybatisPlusProperties.getPage().getMaxLimit());
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        // 4.字段填充插件
        interceptor.addInnerInterceptor(new MyBatisAutoFillInterceptor());
        return interceptor;
    }
}
