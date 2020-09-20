package com.xiaohui.pushsc.cc.infranstructure.persist.jpa.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

/**
 * //todo 配置相关的操作，属于需要审计的部分。 等用户建模后应当增加
 *
 * @author xiaohui
 * @date 2020-09-17
 */
@Entity
@Getter
@NoArgsConstructor
public class ConfigProperties extends AbstractPersistable<Long> {

    private String propKey;
    private String propValue;

    public ConfigProperties(String propKey, String propValue) {
        this.propKey = propKey;
        this.propValue = propValue;
    }
}
