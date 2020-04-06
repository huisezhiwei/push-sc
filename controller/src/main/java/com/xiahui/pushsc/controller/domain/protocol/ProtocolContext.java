package com.xiahui.pushsc.controller.domain.protocol;

import com.xiahui.pushsc.controller.domain.target.TargetSelector;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 协议上下文
 *
 * @author xiaohui
 * create on 2020-04-06
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ProtocolContext {

    private Protocol protocol;
    private TargetSelector targetSelector;

}
