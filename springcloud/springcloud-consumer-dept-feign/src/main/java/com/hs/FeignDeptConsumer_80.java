package com.hs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


//Ribbon和Eureka整合后，通过服务名就可以直接调用，不在关心IP及Port
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.hs"})
@ComponentScan("com.hs")
public class FeignDeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignDeptConsumer_80.class,args);
    }
}
