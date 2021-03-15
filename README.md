# xxl-job-spring-boot-starter

将此项目下载到本地， 执行`gradle build`，将生成的`xxl-job-spring-boot-starter-1.0-SNAPSHOT.jar`导入项目依赖中

在`spring boot`的`application.yml`添加如下配置：

```yaml
### xxl-job配置
xxl-job:
  enabled: true # 启用xxl-job
  admin: # 配置admin属性项
    addresses: http://127.0.0.1:8888/xxl-job-admin # 调度中心地址，多个地址用逗号分开
  executor: # 执行器属性项
    appname: xxl-job-executor-test # 配置任务名称
    port: 9998 # 配置端口
    logpath: /data/applogs/xxl-job/jobhandler # 配置日志路径
    logretentiondays: 30 # 配置日志保留时间
```

在项目中添加任务job，代码如下：

```java
// TestJob.java
package job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestJob {

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        log.info("demo job handler start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        XxlJobHelper.log("XXL-JOB, Hello World.");

        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
        }
        // default success
        log.info("demo job handler end<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
```

