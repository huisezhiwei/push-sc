package com.xiahui.pushsc.controller.domain.target;

import com.xiahui.pushsc.controller.domain.protocol.param.TargetParam;
import com.xiahui.pushsc.controller.domain.target.usecase.ImmediateSelector;
import org.springframework.stereotype.Component;

/**
 * @author xiaohui
 * create on 2020-04-06
 */
@Component
public class TargetSelectorFactory {

    public TargetSelector getTargetSelector(TargetParam targetParam) {

        String useCase = targetParam.getUseCase();

        switch (useCase) {
            case "immediate":
                return new ImmediateSelector(targetParam);
            default:
                return new ImmediateSelector(targetParam);
        }
    }
}
