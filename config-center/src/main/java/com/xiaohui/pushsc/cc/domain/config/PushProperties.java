package com.xiaohui.pushsc.cc.domain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiaohui
 * create on 2020-03-29
 */
@ConfigurationProperties(prefix = "push")
@Setter
@Getter
public class PushProperties {

    private String version;


    public static class Consumer {


    }

    public static class Processor {

    }

    public static class Logger {

    }
}
