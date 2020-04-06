package com.xiahui.pushsc.controller.domain.protocol.param;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 消息目标参数
 *
 * @author xiaohui
 * create on 2020-04-06
 */
@Data
public class TargetParam {

    /**
     * 预定义的使用场景
     */
    private String useCase;
    private JSONObject params;

}
