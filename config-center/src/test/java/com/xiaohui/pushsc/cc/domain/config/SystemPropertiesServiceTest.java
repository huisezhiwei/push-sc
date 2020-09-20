package com.xiaohui.pushsc.cc.domain.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
}