package com.xiaohui.pushsc.cc.infranstructure.persist.jpa;

import com.xiaohui.pushsc.cc.domain.upstream.Config;
import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.imp.BusinessSystemEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiaohui
 * @date 2020-09-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class BusinessSystemJPARepositoryTest {

    @Autowired
    private BusinessSystemJPARepository jpaRepository;

    @Test
    public void insert() {

        Config config = new Config(false, "sms", false, null);
        BusinessSystemEntity entity = new BusinessSystemEntity("test", "test", "test", config);
        jpaRepository.save(entity);

    }
}