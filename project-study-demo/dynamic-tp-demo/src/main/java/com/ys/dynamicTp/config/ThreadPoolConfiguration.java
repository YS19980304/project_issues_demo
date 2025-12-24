package com.ys.dynamicTp.config;

import org.dromara.dynamictp.core.executor.DtpExecutor;
import org.dromara.dynamictp.core.executor.OrderedDtpExecutor;
import org.dromara.dynamictp.core.support.DynamicTp;
import org.dromara.dynamictp.core.support.ThreadPoolBuilder;
import org.dromara.dynamictp.core.support.ThreadPoolCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.dromara.dynamictp.common.em.QueueTypeEnum.MEMORY_SAFE_LINKED_BLOCKING_QUEUE;
import static org.dromara.dynamictp.common.em.RejectedTypeEnum.CALLER_RUNS_POLICY;

/**
 * @author ys
 * @since 2025-12-23
 */
@Configuration
public class ThreadPoolConfiguration {


    /**
     * 通过{@link ThreadPoolBuilder} 设置详细参数创建动态线程池
     * tips: 建议直接在配置中心配置就行，不用@Bean声明
     *
     * @return 线程池实例
     */
    @Bean
    public ThreadPoolExecutor customizeDtpExecutor() {
        return ThreadPoolBuilder.newBuilder()
                .threadPoolName("customizeDtpExecutor")
                .threadFactory("customize-dtp-common")
                .corePoolSize(10)
                .maximumPoolSize(15)
                .keepAliveTime(40)
                .timeUnit(TimeUnit.SECONDS)
                .workQueue(MEMORY_SAFE_LINKED_BLOCKING_QUEUE.getName(), 2000)
                .buildDynamic();
    }

}
