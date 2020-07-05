package com.hs;

import com.myrule.MyOwnRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

//Ribbon和Eureka整合后，通过服务名就可以直接调用，不在关心IP及Port
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(value = "SPRINGCLOUD-PROVIDER-DEPT",configuration = MyOwnRule.class)
public class DeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_80.class,args);
    }
}
