package com.xiaohui.pushsc.cc.domain.base;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * 账户标识
 *
 * @author xiaohui
 * create on 2020-03-28
 */
@Embeddable
public class AccountId implements Serializable {

    private String accountNumber;

    private String accountType;

    public AccountId(String accountNumber, String accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    public AccountId() {
    }
}
