package com.xiaohui.pushsc.cc.domain.config;

import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.ConfigPropertiesRepository;
import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.properties.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.Transformers;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaohui
 * create on 2020-09-18
 */
@Service
public class SystemPropertiesService {

    private final static String CONFIG_PREFIX = "system.";

    @Autowired
    private ConfigPropertiesRepository propertiesRepository;

    public SystemProperties queryFromDb() {

        List<ConfigProperties> configProperties = propertiesRepository.findByPropKeyStartsWith(CONFIG_PREFIX);
        if (configProperties.isEmpty()) {
            return new SystemProperties();
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
    }

}
