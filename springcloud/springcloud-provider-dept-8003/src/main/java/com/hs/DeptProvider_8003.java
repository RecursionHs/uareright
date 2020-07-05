package com.hs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hsir
 */
@SpringBootApplication
@EnableEurekaClient//在服务启动后自动注册到eureka中
@EnableDiscoveryClient//服务发现
public class DeptProvider_8003 {

    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_8003.class);
    }
}
