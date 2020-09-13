package com.xiaohui.pushsc.cc.infranstructure.persist.jpa.imp;

import com.xiaohui.pushsc.cc.domain.upstream.AbstractBusinessSystem;
import com.xiaohui.pushsc.cc.domain.upstream.Config;
import lombok.AccessLevel;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaohui
 * create on 2020-09-14
 */
@Table(name = "tb_message_source")
@Entity
public class BusinessSystemEntity extends AbstractBusinessSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToMany(orphanRemoval = true, mappedBy = "source", cascade = CascadeType.ALL)
    protected List<ConfigParams> paramsList = new ArrayList<>();


    @Override
    public Config getConfig() {
        return null;
    }


}
