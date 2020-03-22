package com.xiaohui.pushsc.source;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 * @author xiaohui
 * create on 2020-03-16
 */
@SpringBootApplication
@EnableSpringConfigured
public class ApplicationSM {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationSM.class).run(args);
    }

}
