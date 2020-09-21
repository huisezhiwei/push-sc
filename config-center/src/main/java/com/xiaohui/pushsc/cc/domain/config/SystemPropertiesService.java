package com.xiaohui.pushsc.cc.domain.config;

import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.ConfigPropertiesRepository;
import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.properties.ConfigProperties;
import com.xiaohui.pushsc.protocol.config.SystemProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.zookeeper.config.ZookeeperConfigProperties;
import org.springframework.integration.dsl.Transformers;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaohui
 * create on 2020-09-18
 */
@Service
public class SystemPropertiesService implements InitializingBean {

    private final static String CONFIG_PREFIX = "system.";

    @Autowired
    private CuratorFramework curator;
    @Autowired
    private ZookeeperConfigProperties zookeeperConfigProperties;

    private String pathRoot;


    @Autowired
    private ConfigPropertiesRepository propertiesRepository;

    public SystemProperties queryFromDb() {

        List<ConfigProperties> configProperties = propertiesRepository.findByPropKeyStartsWith(CONFIG_PREFIX);
        if (configProperties.isEmpty()) {
            SystemProperties properties = new SystemProperties();
            persist(properties);
            return properties;
        } else {

            Map<String, Object> element = new HashMap<>();

            for (ConfigProperties properties : configProperties) {

                element.put(properties.getPropKey().replace(CONFIG_PREFIX, ""), properties.getPropValue());

            }

            SystemProperties payload = (SystemProperties) Transformers.fromMap(SystemProperties.class)
                    .transform(MessageBuilder.withPayload(element).build()).getPayload();

            return payload;
        }

    }

    @Transactional
    public void persist(SystemProperties properties) {

        List<ConfigProperties> configProperties = propertiesRepository.findByPropKeyStartsWith(CONFIG_PREFIX);

        Map<String, Object> result = (Map<String, Object>) Transformers.toMap(true)
                .doTransform(MessageBuilder.withPayload(properties).build());

        List<ConfigProperties> collect = result.entrySet().stream()
                .map(entry -> new ConfigProperties(CONFIG_PREFIX + entry.getKey(), String.valueOf(entry.getValue())))
                .collect(Collectors.toList());

        propertiesRepository.deleteInBatch(configProperties);
        propertiesRepository.saveAll(collect);

        for (ConfigProperties prop : collect) {
            try {

                Stat stat = curator.checkExists().forPath(pathRoot + "/" + prop.getPropKey());
                if (stat == null) {
                    curator.create().creatingParentsIfNeeded().forPath(pathRoot + "/" + prop.getPropKey(), prop.getPropValue().getBytes(StandardCharsets.UTF_8));
                } else {
                    curator.setData().forPath(pathRoot + "/" + prop.getPropKey(), prop.getPropValue().getBytes(StandardCharsets.UTF_8));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //        if (!applicationName.startsWith("/")) {
//            baseContext.append("/");
//        }
//        baseContext.append(applicationName);
        pathRoot = "/" + zookeeperConfigProperties.getRoot() + "/" + zookeeperConfigProperties.getDefaultContext();
    }
}
