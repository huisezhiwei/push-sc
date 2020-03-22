package com.xiaohui.pushsc.source.api.http;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author xiaohui
 * create on 2020-03-16
 */
@RestController
@RequestMapping("/")
public class ServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/service")
    public ResponseEntity<String> serviceList() {

        // 此处返回了服务实例
        List<String> services = discoveryClient.getServices();


        return new ResponseEntity<>(JSON.toJSONString(services), HttpStatus.OK);

    }

    @GetMapping("/service/detail/{service}")
    public ResponseEntity<String> serviceDetail(@PathVariable String service) {

        List<ServiceInstance> instances = discoveryClient.getInstances(service);

        for (ServiceInstance instance : instances) {
            System.out.println(instance.getUri().toString());
        }

        return new ResponseEntity<>(JSON.toJSONString(instances), HttpStatus.OK);
    }

    //todo 这里应该用post ，此处假实现
    @GetMapping("/service/invoke/{service}")
    public ResponseEntity<String> serviceInvoke(@PathVariable String service) {

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://" + service + "/dict/detail/1", String.class);

        String body = forEntity.getBody();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
