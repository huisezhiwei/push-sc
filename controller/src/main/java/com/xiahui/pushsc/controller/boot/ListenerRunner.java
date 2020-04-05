package com.xiahui.pushsc.controller.boot;

import com.alibaba.fastjson.JSON;
import com.xiaohui.pushsc.protocol.constants.ServiceInstance;
import com.xiaohui.pushsc.protocol.source.MessageProducerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiaohui
 * create on 2020-04-05
 */
@Component
public class ListenerRunner implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {

        ResponseEntity<MessageProducerResource[]> responseEntity = restTemplate.getForEntity("http://" + ServiceInstance.SOURCE_MANAGER + "/msgProducer/list", MessageProducerResource[].class);
        MessageProducerResource[] entityBody = responseEntity.getBody();
        for (MessageProducerResource resource : entityBody) {
            System.out.println(JSON.toJSONString(resource));
        }

    }
}
