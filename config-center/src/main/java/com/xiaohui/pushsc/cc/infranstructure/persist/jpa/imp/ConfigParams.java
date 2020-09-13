package com.xiaohui.pushsc.cc.infranstructure.persist.jpa.imp;

import javax.persistence.*;

/**
 * @author xiaohui
 * create on 2020-09-14
 */
@Table(name = "tb_message_source_extend")
@Entity
public class ConfigParams {

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
