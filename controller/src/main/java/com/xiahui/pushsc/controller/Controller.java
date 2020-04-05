package com.xiahui.pushsc.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiaohui
 * create on 2020-03-16
 */
@SpringBootApplication
@EnableSpringConfigured
public class Controller {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();

    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Controller.class).run(args);
    }

}
