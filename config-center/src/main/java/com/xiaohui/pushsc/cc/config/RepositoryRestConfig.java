package com.xiaohui.pushsc.cc.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.xiaohui.pushsc.cc.domain.dict.model.Dictionary;
import com.xiaohui.pushsc.protocol.source.DictionaryResource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import java.io.IOException;

/**
 * @author xiaohui
 * create on 2020-03-16
 */
@Configuration
public class RepositoryRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new SimpleModule() {
            @Override
            public void setupModule(SetupContext context) {

                SimpleSerializers serializers = new SimpleSerializers();
                SimpleDeserializers deserializers = new SimpleDeserializers();

                serializers.addSerializer(Dictionary.class, new JsonSerializer<Dictionary>() {
                    @Override
                    public void serialize(Dictionary value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                        gen.writeStartObject();
                        gen.writeStringField("code",value.getDictCode());
                        gen.writeEndObject();
                    }
                });
                deserializers.addDeserializer(Dictionary.class, new JsonDeserializer<Dictionary>() {
                    @Override
                    public Dictionary deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                        ObjectMapper mapper = new ObjectMapper();
                        DictionaryResource resource = mapper.treeToValue(p.readValueAsTree(), DictionaryResource.class);

                        return new Dictionary(resource);
                    }
                });

                context.addSerializers(serializers);
                context.addDeserializers(deserializers);

            }
        });
    }
}
