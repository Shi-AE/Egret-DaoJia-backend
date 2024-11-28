package com.edj.xxl.job.config;

import com.edj.common.utils.ObjectUtils;
import com.edj.common.utils.StringUtils;
import com.edj.xxl.job.properties.XxlJobProperties;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "xxl-job", value = "enable", havingValue = "true")
@EnableConfigurationProperties(XxlJobProperties.class)
public class XxlJobConfiguration {

    @Bean
    public XxlJobSpringExecutor xxlJobSpringExecutor(XxlJobProperties prop) {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();

        String accessToken = prop.getAccessToken();
        xxlJobSpringExecutor.setAccessToken(accessToken);
        log.debug("accessToken:{}", accessToken);

        XxlJobProperties.Admin admin = prop.getAdmin();
        if (ObjectUtils.isNotNull(admin) && StringUtils.isNotBlank(admin.getAddress())) {
            String address = admin.getAddress();
            xxlJobSpringExecutor.setAdminAddresses(address);
            log.debug("address:{}", address);
        }

        XxlJobProperties.Executor executor = prop.getExecutor();
        if (ObjectUtils.isNotNull(executor)) {
            String ip = executor.getIp();
            xxlJobSpringExecutor.setIp(ip);
            log.debug("ip:{}", ip);
            String appName = executor.getAppName();
            xxlJobSpringExecutor.setAppname(appName);
            log.debug("appName:{}", appName);
            Integer port = executor.getPort();
            xxlJobSpringExecutor.setPort(port);
            log.debug("port:{}", port);
            String logPath = executor.getLogPath();
            xxlJobSpringExecutor.setLogPath(logPath);
            log.debug("logPath:{}", logPath);
            Integer logRetentionDays = executor.getLogRetentionDays();
            xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
            log.debug("logRetentionDays:{}", logRetentionDays);
        }

        log.info(">>>>>>>>>>> xxl-job config end.");
        return xxlJobSpringExecutor;
    }
}
