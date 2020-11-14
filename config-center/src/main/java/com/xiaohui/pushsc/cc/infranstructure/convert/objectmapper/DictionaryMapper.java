package com.xiaohui.pushsc.cc.infranstructure.convert.objectmapper;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.xiaohui.pushsc.cc.domain.dict.model.Dictionary;
import com.xiaohui.pushsc.protocol.source.DictionaryResource;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;

/**
 * @author xiaohui
 * @date 2020-09-17
 */
public class DictionaryMapper extends SimpleModule {

    @Override
    public void setupModule(SetupContext context) {

        SimpleSerializers serializer = new SimpleSerializers();
        serializer.addSerializer(Dictionary.class, new JsonSerializer<Dictionary>() {

            @Override
            public void serialize(Dictionary value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeNumberField("id", value.getId());
                gen.writeStringField("code", value.getDictCode());
                gen.writeStringField("key", value.getDictKey());
                gen.writeStringField("value", value.getDictValue());
                gen.writeStringField("scope", value.getScope());
                gen.writeNumberField("sort", value.getSort() != null ? value.getSort() : 0);
                gen.writeBooleanField("active", value.getActive() != null ? value.getActive() : false);
                gen.writeNumberField("parentId", value.getParentId());
                gen.writeArrayFieldStart("subIds");
                for (Dictionary dictionary : value.subDictionarySet()) {
                    gen.writeNumber(dictionary.getId());
                }
                gen.writeEndArray();
            }

            @Override
            public boolean isUnwrappingSerializer() {
                return true;
            }
        });
        context.addSerializers(serializer);

        SimpleDeserializers deserializers = new SimpleDeserializers();

        deserializers.addDeserializer(Dictionary.class, new JsonDeserializer<Dictionary>() {
            @Override
            public Dictionary deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                JsonNode node = p.getCodec().readTree(p);
                ObjectMapper op = new ObjectMapper();
                DictionaryResource resource = op.convertValue(node, DictionaryResource.class);

                return new Dictionary(resource);
            }
        });

        context.addDeserializers(deserializers);

    }
}
