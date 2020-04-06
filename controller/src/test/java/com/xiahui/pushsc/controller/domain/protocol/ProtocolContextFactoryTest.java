package com.xiahui.pushsc.controller.domain.protocol;

import com.alibaba.fastjson.JSONObject;
import com.xiahui.pushsc.controller.domain.protocol.param.TargetParam;
import com.xiahui.pushsc.controller.domain.target.TargetSelector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * @author xiaohui
 * create on 2020-04-06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProtocolContextFactoryTest {

    @Autowired
    private ProtocolContextFactory factory;

    // 网上讨论，说此类语法糖会导致内存溢出问题，故在非测试类中不建议使用
    private static JSONObject testProtocol = new JSONObject() {{
        put("version", 1);
        put("sequence", "123456");
        put("sign", "abc");
        put("sendTime", System.currentTimeMillis());
        put("target", new TargetParam() {{
            setUseCase("immediate");
            setParams(new JSONObject() {{
                put("items", new ArrayList<String>() {{
                    add("18060849990");
                    add("18060849991");
                    add("18060849992");
                    add("18060849993");
                }});
//                put("accountType", "default");
            }});
        }});
    }};

    @Test
    public void createContext() {

        ProtocolContext context = factory.createContext(testProtocol.toJavaObject(Protocol.class));
        TargetSelector targetSelector = context.getTargetSelector();
        System.out.println(targetSelector.getTargetPage().toJSONString());
        System.out.println(targetSelector.getTargetCount());

    }
}