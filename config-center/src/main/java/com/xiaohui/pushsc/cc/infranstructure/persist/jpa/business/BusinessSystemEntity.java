package com.xiaohui.pushsc.cc.infranstructure.persist.jpa.business;

import com.xiaohui.pushsc.cc.domain.upstream.AbstractBusinessSystem;
import com.xiaohui.pushsc.cc.domain.upstream.Config;
import com.xiaohui.pushsc.protocol.source.BusinessSystemResource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaohui
 * create on 2020-09-14
 */
@Table(name = "tb_message_source")
@Entity
@NoArgsConstructor
@RepositoryEventHandler
public class BusinessSystemEntity extends AbstractBusinessSystem {

    public BusinessSystemEntity(String code, String secret, String note, Config config) {

        this.code = code;
        this.secret = secret;
        this.note = note;

        if (config == null) {
            this.paramsList = new ArrayList<>();
        } else {

            paramsList = config.configMap().entrySet().stream()
                    .map(entry -> new ConfigParams(entry.getKey(), entry.getValue(), this))
                    .collect(Collectors.toList());

        }

    }

    void replaceConfigParams() {
        if (!updateList.isEmpty()) {
            this.paramsList.clear();
            this.paramsList.addAll(updateList.stream()
                    .map(item -> new ConfigParams(item.getCode(), item.getValue(), this))
                    .collect(Collectors.toList()));
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToMany(orphanRemoval = true, mappedBy = "source", cascade = CascadeType.ALL)
    protected List<ConfigParams> paramsList = new ArrayList<>();

    @Setter(AccessLevel.PUBLIC)
    private transient List<ConfigParams> updateList = new ArrayList<>();


    public BusinessSystemEntity(BusinessSystemResource resource) {
        super();
        this.code = resource.getCode();
        this.secret = resource.getSecret();
        this.note = resource.getNote();
        this.active = resource.isActive();
        this.updateList = resource.getParams().stream()
                .map(item -> new ConfigParams(item.getCode(), item.getValue(), this))
                .collect(Collectors.toList());
    }

    public void setParamsList(List<ConfigParams> paramsList) {
        this.paramsList = paramsList;
    }

    protected transient Config config;

    @Override
    public Config getConfig() {

        if (config == null) {
            Map<String, String> paramMap = paramsList.stream()
                    .filter(element -> StringUtils.hasText(element.getCode()))
                    .collect(Collectors.toMap(ConfigParams::getCode, ConfigParams::getValue));
            Assert.isTrue(paramMap.size() == paramsList.size(), "消息源扩展配置格式异常");
            config = new Config(paramMap);
        }

        return config;

    }


}
