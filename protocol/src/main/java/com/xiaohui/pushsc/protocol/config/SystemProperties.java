package com.xiaohui.pushsc.protocol.config;

import lombok.Data;

/**
 * 系统参数
 * 这些参数，需要能够直接同步到zk中
 * 属性的设置和结果，最好能直接映射到对应的表中，每次增加属性时无需再次读取
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
