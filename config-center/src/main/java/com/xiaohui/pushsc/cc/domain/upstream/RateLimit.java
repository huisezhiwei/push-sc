package com.xiaohui.pushsc.cc.domain.upstream;

import lombok.Value;

/**
 * @author xiaohui
 * create on 2020-09-14
 */
@Value
class RateLimit {

    private long refreshRate;
    private long refreshCount;
    private long initCount;

}
