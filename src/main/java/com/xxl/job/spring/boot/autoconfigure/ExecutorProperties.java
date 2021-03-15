package com.xxl.job.spring.boot.autoconfigure;

import lombok.Data;

@Data
public class ExecutorProperties {
    private String appName;
    private String logPath;
    private String ip;
    private Integer port = 9999;
    private Integer logRetentionDays = -1;
}
