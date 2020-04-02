package com.xiaohui.pushsc.cc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置中心
 */
@SpringBootApplication
@EnableSpringConfigured
@ConfigurationPropertiesScan
public class ApplicationCC {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationCC.class).run(args);
    }


}
