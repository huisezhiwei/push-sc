package com.xiaohui.pushsc.cc.domain.base;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author xiaohui
 * create on 2020-03-28
 */
@Entity
public class Account {

    @EmbeddedId
    private AccountId accountNumber;


    public Account(AccountId accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Account() {
    }
}
