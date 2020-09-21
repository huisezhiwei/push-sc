package com.xiaohui.pushsc.cc.config;

import com.xiaohui.pushsc.protocol.config.SystemProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaohui
 * create on 2020-09-21
 */
@Configuration
public class InitConfig {

    @Bean
    @ConfigurationProperties(prefix = "system")
    public SystemProperties systemProperties() {
        return new SystemProperties();
    }

}
