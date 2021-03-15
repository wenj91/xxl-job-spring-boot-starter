package com.xxl.job.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("xxl-job")
public class XxlJobProperties {
    private boolean enabled = false;
    private String accessToken;
    private AdminProperties admin;
    private ExecutorProperties executor;
}
