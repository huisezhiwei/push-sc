package com.xiahui.pushsc.controller.boot;

import com.alibaba.fastjson.JSON;
import com.xiaohui.pushsc.protocol.constants.ServiceInstance;
import com.xiaohui.pushsc.protocol.source.MessageProducerResource;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiaohui
 * create on 2020-04-05
 */
@Component
public class ListenerRunner implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private KafkaProperties kafkaProperties;

    @Override
    public void run(String... args) throws Exception {

        ResponseEntity<MessageProducerResource[]> responseEntity = restTemplate.getForEntity("http://" + ServiceInstance.SOURCE_MANAGER + "/msgProducer/list", MessageProducerResource[].class);
        MessageProducerResource[] entityBody = responseEntity.getBody();
        createListener(entityBody);
    }

    private void createListener(MessageProducerResource[] resource) {

        List<String> topics = Stream.of(resource).map(MessageProducerResource::getCode).map(code -> "topic-" + code).collect(Collectors.toList());
        ContainerProperties containerProp = new ContainerProperties(topics.toArray(new String[]{}));
        containerProp.setGroupId("push-controller");
        containerProp.setAckMode(ContainerProperties.AckMode.MANUAL);
        containerProp.setMessageListener(new AcknowledgingMessageListener<String, String>() {
            @Override
            public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {
                try {
                    String value = data.value();
                    System.out.println(value);
                    //todo 逻辑入口
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    acknowledgment.acknowledge();
                }
            }
        });

        DefaultKafkaConsumerFactory<String, String> factory = new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
        KafkaMessageListenerContainer<String, String> listenerContainer = new KafkaMessageListenerContainer<>(factory, containerProp);
        listenerContainer.setBeanName("消息源监听器");
        listenerContainer.start();

    }
}
