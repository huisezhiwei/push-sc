package com.xiaohui.pushsc.cc.infranstructure.persist.jpa;

import com.xiaohui.pushsc.cc.domain.account.Account;
import com.xiaohui.pushsc.cc.domain.account.AccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 测试用接口,不是正式实现
 * @author xiaohui
 * create on 2020-11-16
 */
@RepositoryRestResource(path = "accounts")
public interface AccountJPARepository extends JpaRepository<Account, AccountId> {
}
