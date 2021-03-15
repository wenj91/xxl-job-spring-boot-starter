package com.xxl.job.spring.boot.autoconfigure;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Slf4j
@Configuration
@ConditionalOnClass(XxlJobSpringExecutor.class)
@ConditionalOnProperty(prefix = "xxl-job", name = "enabled", havingValue = "true") // 必须配置 xxl-job.enabled=true 才会生效
@EnableConfigurationProperties({XxlJobProperties.class})
public class XxlJobAutoConfiguration {

    @Autowired
    private XxlJobProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public XxlJobExecutor xxlJobExecutor() {
        log.info("xxl-job config init>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Objects.requireNonNull(properties, "xxl job properties must be not null.");

        AdminProperties adminProperties = this.properties.getAdmin();
        Objects.requireNonNull(adminProperties, "xxl job admin properties must be not null.");

        ExecutorProperties executorProperties = this.properties.getExecutor();
        Objects.requireNonNull(executorProperties, "xxl job executor properties must be not null.");

        XxlJobExecutor xxlJobExecutor = new XxlJobSpringExecutor();
        xxlJobExecutor.setIp(executorProperties.getIp());
        xxlJobExecutor.setPort(executorProperties.getPort());
        xxlJobExecutor.setAppname(executorProperties.getAppName());
        xxlJobExecutor.setLogPath(executorProperties.getLogPath());
        xxlJobExecutor.setLogRetentionDays(executorProperties.getLogRetentionDays());
        xxlJobExecutor.setAdminAddresses(adminProperties.getAddresses());
        xxlJobExecutor.setAccessToken(this.properties.getAccessToken());

        return xxlJobExecutor;
    }
}
