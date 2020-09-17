package com.xiaohui.pushsc.cc.infranstructure.convert.objectmapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.business.BusinessSystemEntity;
import com.xiaohui.pushsc.protocol.source.BusinessSystemResource;

import java.io.IOException;

/**
 * @author xiaohui
 * @date 2020-09-17
 */
public class BusinessMapper extends SimpleModule {

    @Override
    public void setupModule(SetupContext context) {


        // 从json，序列化成对象是没有问题的。
        SimpleDeserializers deserializers = new SimpleDeserializers();
        deserializers.addDeserializer(BusinessSystemEntity.class, new JsonDeserializer<BusinessSystemEntity>() {
            @Override
            public BusinessSystemEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                ObjectMapper mapper = new ObjectMapper();
                BusinessSystemResource resource = mapper.treeToValue(p.readValueAsTree(), BusinessSystemResource.class);

                return new BusinessSystemEntity(resource);
            }
        });

        context.addDeserializers(deserializers);

    }
}
