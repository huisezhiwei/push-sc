package com.xiahui.pushsc.controller.domain.target;

import com.alibaba.fastjson.JSONObject;

/**
 * 从业务的角度来看， 叫做目标选择器更好
 *
 * @author xiaohui
 * create on 2020-04-06
 */
public interface TargetSelector {

    /**
     * 返回使用场景，其实就是类型标识
     *
     * @return 类型标识
     */
    String getUseCase();

    /**
     * 目标数量
     *
     * @return 报文所对应的目标数量
     */
    long getTargetCount();

    /**
     * 分页返回具体的目标信息
     *
     * @return 此处的返回类型是预设
     */
    JSONObject getTargetPage();

}
