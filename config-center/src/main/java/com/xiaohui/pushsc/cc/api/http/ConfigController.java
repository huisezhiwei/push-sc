package com.xiaohui.pushsc.cc.api.http;

import com.xiaohui.pushsc.cc.domain.config.PushProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaohui
 * create on 2020-03-29
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${version}")
    private String version;

    @Autowired
    private PushProperties properties;

    @GetMapping("/version")
    public String showVersion() {

        return properties.getVersion();

    }

}
