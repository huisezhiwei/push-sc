package com.xiaohui.pushsc.cc.domain.config;

import lombok.Data;

/**
 * 系统参数
 *
 * @author xiaohui
 * @date 2020-09-17
 */
@Data
public class SystemProperties {

    private String version = "1.0.0";
    private DataSource dataSource = new DataSource();
    private Redis redis = new Redis();
    private Kafka kafka = new Kafka();


    @Data
    public static class DataSource {
        private String dsUrl = "localhost";
        private String dsUser = "user";
        private String dsPassword = "password";
    }

    @Data
    public static class Redis {
        private String host = "host";
        private String port = "port";
        private String password = "password";
    }

    @Data
    public static class Kafka {
        private String server = "localhost";
    }

}
