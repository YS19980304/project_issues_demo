package com.ys.dynamicTp;

import org.dromara.dynamictp.spring.annotation.EnableDynamicTp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDynamicTp
@ComponentScan(basePackages = {"com.ys.dynamicTp.*"})
@EnableDiscoveryClient
@SpringBootApplication
public class TestCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCloudApplication.class, args);
    }

}
