package com.xiahui.pushsc.controller.domain.protocol;

import com.xiahui.pushsc.controller.domain.protocol.param.TargetParam;
import com.xiahui.pushsc.controller.domain.target.TargetSelector;
import com.xiahui.pushsc.controller.domain.target.TargetSelectorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiaohui
 * create on 2020-04-06
 */
@Component
public class ProtocolContextFactory {

    @Autowired
    private TargetSelectorFactory selectorFactory;

    public ProtocolContext createContext(Protocol protocol) {
        TargetParam target = protocol.getTarget();
        TargetSelector targetSelector = selectorFactory.getTargetSelector(target);
        return new ProtocolContext(protocol, targetSelector);
    }

}
