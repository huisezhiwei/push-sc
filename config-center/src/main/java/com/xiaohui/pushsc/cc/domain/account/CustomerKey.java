package com.xiaohui.pushsc.cc.domain.account;

import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 自定义复合主键
 *
 * @author xiaohui
 * create on 2020-11-16
 */
@Component
public class CustomerKey implements BackendIdConverter {

    @Override
    public Serializable fromRequestId(String id, Class<?> entityType) {
        if (entityType == Account.class) {
            String[] split = id.split("_");
            return new AccountId(split[0], split[1]);
        }
        return id;
    }

    @Override
    public String toRequestId(Serializable id, Class<?> entityType) {
        if (entityType == Account.class) {
            AccountId accountId = (AccountId) id;
            return accountId.getAccountNumber() + "_" + accountId.getAccountType();
        }
        return id.toString();
    }

    @Override
    public boolean supports(Class<?> delimiter) {
        return true;
    }
}
