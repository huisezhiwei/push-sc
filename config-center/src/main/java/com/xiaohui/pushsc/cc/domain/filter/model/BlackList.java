package com.xiaohui.pushsc.cc.domain.filter.model;

import com.xiaohui.pushsc.cc.domain.account.Account;
import com.xiaohui.pushsc.cc.domain.account.AccountId;
import com.xiaohui.pushsc.cc.infranstructure.persist.base.EntityModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * 消息推送黑名单
 *
 * @author xiaohui
 * create on 2020-03-28
 */
@Entity
@Getter
@NoArgsConstructor
public class BlackList extends EntityModel<Long> {

    private String code;
    private String type;

    private String nick;
    private String remark;


    @OneToMany
    /**
     * 黑名单中包含的账号信息
     */
    private List<Account> identifies;


    /**
     * 增加账户
     *
     * @param accountId
     */
    public void appendAccount(AccountId accountId) {
        identifies.add(new Account(accountId));
    }

}
