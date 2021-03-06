package com.xiaohui.pushsc.cc.infranstructure.persist.jpa.business;

import com.xiaohui.pushsc.cc.infranstructure.persist.base.EntityModel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author xiaohui
 * create on 2020-09-14
 */
@Table(name = "message_source_extend")
@NoArgsConstructor
@Entity
public class ConfigParams extends EntityModel<Long> {

    public ConfigParams(String code, String value, BusinessSystemEntity source) {
        this.code = code;
        this.value = value;
        this.source = source;
    }

    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private BusinessSystemEntity source;
}
