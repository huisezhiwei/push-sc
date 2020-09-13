package com.xiaohui.pushsc.cc.domain.upstream;

import javax.persistence.MappedSuperclass;

/**
 * 业务系统
 *
 * @author xiaohui
 * create on 2020-09-13
 */
//todo 子类如果要识别变量，需要这个注解
@MappedSuperclass
public abstract class AbstractBusinessSystem implements DataSource {

    protected String systemCode;
    protected String systemSecret;
    protected String systemDescribe;
    protected boolean active;

    @Override
    public String getSourceCode() {
        return systemCode;
    }

    @Override
    public String getSecret() {
        return systemSecret;
    }

    @Override
    public String getMessageChannel() {
        return systemCode + "-message";
    }

    @Override
    public String getAckChannel() {
        return systemCode + "-ack";
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
