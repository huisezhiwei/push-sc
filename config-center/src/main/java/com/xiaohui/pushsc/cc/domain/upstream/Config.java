package com.xiaohui.pushsc.cc.domain.upstream;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息源配置
 *
 * @author xiaohui
 * create on 2020-09-14
 */
@AllArgsConstructor
@Getter
public class Config {

    private boolean forceTemplate;
    private String limitChannels;
    private boolean checkSensitiveWord;
    private RateLimit rateLimit;


    public Config(Map<String, String> paramMap) {

        this.forceTemplate = Boolean.parseBoolean(paramMap.getOrDefault("forceTemplate", "0"));
        this.limitChannels = paramMap.getOrDefault("limitChannels", "");
        this.checkSensitiveWord = Boolean.parseBoolean(paramMap.getOrDefault("checkSensitiveWord", "0"));

        if (paramMap.containsKey("rateLimit.refreshRate") && paramMap.containsKey("rateLimit.refreshCount") && paramMap.containsKey("rateLimit.initCount")) {
            rateLimit = new RateLimit(
                    Long.parseLong(paramMap.get("rateLimit.refreshRate")),
                    Long.parseLong(paramMap.get("rateLimit.refreshCount")),
                    Long.parseLong(paramMap.get("rateLimit.initCount"))
            );
        } else {
            rateLimit = null;
        }

    }

    public Map<String, String> configMap() {

        Map<String, String> map = new HashMap<>();

        map.put("forceTemplate", String.valueOf(forceTemplate));
        map.put("limitChannels", limitChannels);
        map.put("checkSensitiveWord", String.valueOf(checkSensitiveWord));

        if (rateLimit != null) {
            map.put("rateLimit.refreshRate", String.valueOf(rateLimit.getRefreshRate()));
            map.put("rateLimit.refreshCount", String.valueOf(rateLimit.getRefreshCount()));
            map.put("rateLimit.initCount", String.valueOf(rateLimit.getInitCount()));
        }

        return map;

    }
}
