package com.xiaohui.pushsc.source.api.http;

import com.xiaohui.pushsc.protocol.source.MessageProducerResource;
import com.xiaohui.pushsc.source.domain.tenant.Tenant;
import com.xiaohui.pushsc.source.inframstructure.persist.jpa.TenantJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息源Restful接口
 *
 * @author xiaohui
 * create on 2020-04-05
 */
@RestController
@RequestMapping("/msgProducer")
public class MsgProducerController {

    @Autowired
    private TenantJPARepository jpaRepository;

    @GetMapping("/list")
    public ResponseEntity<List<MessageProducerResource>> showTenantList() {

        List<Tenant> tenants = jpaRepository.findAll();
        List<MessageProducerResource> resources = tenants.stream().map(domain -> new MessageProducerResource(domain.getCode(), domain.getSecret()
                , domain.forgeTemplateFlag(), domain.sensitiveFlag(), domain.isAlive())).collect(Collectors.toList());

        return ResponseEntity.ok(resources);

    }

}
