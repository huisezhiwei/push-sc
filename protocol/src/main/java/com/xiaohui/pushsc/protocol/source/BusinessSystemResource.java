package com.xiaohui.pushsc.protocol.source;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务系统资源对象
 *
 * @author xiaohui
 * @date 2020-09-17
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BusinessSystemResource {

    private String code;
    private String secret;
    private String note;
    private boolean active = true;

    private List<Param> params = new ArrayList<>();

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Param {
        String code;
        String value;
    }

}
