package com.xiaohui.pushsc.source.domain.tenant;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xiaohui
 * create on 2020-04-02
 */
@Entity
@Getter
@Setter
public class TenantConfig implements Serializable {

    // 过滤敏感词
    public static final String KEY_SENSITIVE = "filter_sensitive_word";
    // 强制使用模板
    public static final String KEY_FORGE_TEMPLATE = "forget_template";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String configKey;
    private String configValue;

    public TenantConfig() {

    }

    // 好像只有建立双向关联，才能使用自然主键
    @ManyToOne
    @JoinColumn(referencedColumnName = "code")
    private Tenant tenant;

}
