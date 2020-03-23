package com.hs.bootformq_receive.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SysConfig {

        @Bean
        public TomcatServletWebServerFactory servletContainer(){
            TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
            tomcatServletWebServerFactory.setPort(8090);
            return tomcatServletWebServerFactory;
        }
}
