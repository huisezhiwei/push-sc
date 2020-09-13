package com.xiaohui.pushsc.cc.domain.upstream;

/**
 * 消息源
 *
 * @author xiaohui
 * create on 2020-09-13
 */
public interface DataSource {

    String getSourceCode();


    String getSecret();


    String getMessageChannel();


    String getAckChannel();

    Config getConfig();

    boolean isActive();


}
