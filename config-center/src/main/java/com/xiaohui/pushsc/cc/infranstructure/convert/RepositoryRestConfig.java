package com.xiaohui.pushsc.cc.infranstructure.convert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaohui.pushsc.cc.infranstructure.convert.objectmapper.BusinessMapper;
import com.xiaohui.pushsc.cc.infranstructure.convert.objectmapper.DictionaryMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

/**
 * @author xiaohui
 * create on 2020-03-16
 */
@Configuration
public class RepositoryRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new DictionaryMapper());
        objectMapper.registerModule(new BusinessMapper());
    }
}
