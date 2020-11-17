package com.xiaohui.pushsc.cc.domain.account;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaohui
 * create on 2020-03-28
 */
@Entity
@Data
@NoArgsConstructor
public class Account {

    @JsonIgnore
    @EmbeddedId
    private AccountId accountNumber;

    @JsonProperty("nick")
    private String name;
    private int age;

    @JsonCreator
    public Account(@JsonProperty("nick") String name, @JsonProperty("age") int age,
                   @JsonProperty("number") String accountNumber, @JsonProperty("type") String accountType) {
        this.name = name;
        this.age = age;
        this.accountNumber = new AccountId(accountNumber, accountType);
    }

    public Account(AccountId accountNumber) {
        this.accountNumber = accountNumber;
    }


    @JsonAnyGetter
    public Map<String, Object> jsonView() {
        Map<String, Object> result = new HashMap<>();
        result.put("nick", name);
        result.put("age", age);
        result.put("accountNumber", accountNumber.getAccountNumber());
        result.put("accountType", accountNumber.getAccountType());

        return result;
    }

}
