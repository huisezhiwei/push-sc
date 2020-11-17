package com.xiaohui.pushsc.cc.domain.config;

import com.alibaba.fastjson.JSON;
import com.xiaohui.pushsc.protocol.config.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiaohui
 * create on 2020-09-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SystemPropertiesServiceTest {

    @Autowired
    private SystemPropertiesService service;
    @Autowired
    private Environment environment;
    @Autowired
    private SystemProperties systemProperties;

    @Test
    public void queryFromDb() {
        SystemProperties properties = service.queryFromDb();

        log.info(JSON.toJSONString(properties));
    }

    @Test
    public void persist() {
        SystemProperties properties = new SystemProperties();
        service.persist(properties);
    }

    @Test
    public void queryFromContext() {
        log.info(JSON.toJSONString(systemProperties));
    }
}