package com.xiaohui.pushsc.protocol.source;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 消息生产者资源
 *
 * @author xiaohui
 * create on 2020-04-05
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MessageProducerResource {

    private String code;
    private String secret;
    private boolean forgetTemplate;
    private boolean sensitiveWord;

    private boolean active;

}
