package com.xiaohui.pushsc.cc.infranstructure.persist.jpa.imp;

import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author xiaohui
 * create on 2020-09-14
 */
@Table(name = "tb_message_source_extend")
@NoArgsConstructor
@Entity
public class ConfigParams {

    public ConfigParams(String code, String value, BusinessSystemEntity source) {
        this.code = code;
        this.value = value;
        this.source = source;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
