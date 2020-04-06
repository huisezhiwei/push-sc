package com.xiahui.pushsc.controller.domain.protocol;

import com.xiahui.pushsc.controller.domain.protocol.param.ChannelParam;
import com.xiahui.pushsc.controller.domain.protocol.param.ExtendParam;
import com.xiahui.pushsc.controller.domain.protocol.param.MessageParam;
import com.xiahui.pushsc.controller.domain.protocol.param.TargetParam;
import lombok.Data;

/**
 * 请求协议
 *
 * @author xiaohui
 * create on 2020-04-06
 */
@Data
public class Protocol {

    private int version;
    private String sequence;
    private String sign;
    private Long sendTime;

    private TargetParam target;
    private ChannelParam channel;
    private MessageParam message;

    private ExtendParam extend;

}
