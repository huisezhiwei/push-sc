package com.xiaohui.pushsc.source.inframstructure.persist.jpa;

import com.alibaba.fastjson.JSON;
import com.xiaohui.pushsc.source.domain.tenant.Tenant;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author xiaohui
 * create on 2020-04-02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TenantJPARepositoryTest {

    @Autowired
    private TenantJPARepository repository;

    private static Tenant tenant;


    @BeforeClass
    public static void init() {
        tenant = new Tenant("hive", "蜂巢", "123456", "蜂巢测试数据");
        tenant.forgeTemplateFlag(true);
        tenant.sensitiveFlag(true);

    }

    @Test
    public void insert() {
        repository.save(tenant);
    }

    @Test
    public void query() {

        List<Tenant> tenants = repository.findAll();
        for (Tenant domain : tenants) {
            System.out.println(JSON.toJSONString(domain));
        }

    }

}