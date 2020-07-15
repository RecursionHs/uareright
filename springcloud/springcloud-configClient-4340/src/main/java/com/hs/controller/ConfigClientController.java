package com.hs.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
    @Value("${server.port}")
    private String port;

    @Value("${spring.profile}")
    private String profile;

    @Value("${eureka.client.service-url.defaultZone}")
    private String defaultZone;

    @Value("${spring.application.name}")
    private String name;

    @GetMapping("/config")
    public String getConfigProperties(){
        return "ConfigClientController{" +
                "port='" + port + '\'' +
                ", profile='" + profile + '\'' +
                ", defaultZone='" + defaultZone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
