package com.xiaohui.pushsc.cc.domain.upstream;

import lombok.Value;

/**
 * 消息源配置
 *
 * @author xiaohui
 * create on 2020-09-14
 */
@Value
public class Config {

    private boolean forceTemplate;
    private String limitChannels;
    private boolean checkSensitiveWord;
    private RateLimit rateLimit;

}
