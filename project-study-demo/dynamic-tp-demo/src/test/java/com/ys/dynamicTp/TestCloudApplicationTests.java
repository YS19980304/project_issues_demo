package com.ys.dynamicTp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@SpringBootTest
class TestCloudApplicationTests {


    @Resource
    private ThreadPoolExecutor customizeDtpExecutor;


    /**
     * 触发报警
     * <p>
     * nacos配置文件   resources/doc/dynamic-tp-demo.yaml
     *
     */
    @Test
    void callThePolice() {

        // 直接获取
        // DtpExecutor customizeDtpExecutor = DtpRegistry.getDtpExecutor("customizeDtpExecutor");

        // 创建线程,主动耗尽资源
        for (int i = 0; i < 100; i++) {
            CompletableFuture.runAsync(() -> {
                try {
                    // 模拟耗时,主动拉爆线程池
                    Thread.sleep(100000);
                } catch (Exception e) {
                    log.error("======> 线程超时");
                }
            }, customizeDtpExecutor);
        }

        // ================
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
