package com.xiaohui.pushsc.cc.domain.upstream;

import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * 业务系统
 * 需要使用 mappedSuperclass 将属性映射给子类
 * 需要提供 protected 级别的set方法，让子类注入属性
 *
 * @author xiaohui
 * create on 2020-09-13
 */
@MappedSuperclass
@Setter(AccessLevel.PROTECTED)
public abstract class AbstractBusinessSystem implements DataSource {

    protected String code;
    protected String secret;
    protected String note;
    protected boolean active;

    @Override
    public String getSourceCode() {
        return code;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public String getMessageChannel() {
        return code + "-message";
    }

    @Override
    public String getAckChannel() {
        return code + "-ack";
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
