package com.xiaohui.pushsc.source.domain.tenant;

import com.xiaohui.pushsc.source.domain.base.EntityBase;
import lombok.AccessLevel;
import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.xiaohui.pushsc.source.domain.tenant.TenantConfig.KEY_FORGE_TEMPLATE;
import static com.xiaohui.pushsc.source.domain.tenant.TenantConfig.KEY_SENSITIVE;

/**
 * 租户
 *
 * @author xiaohui
 * create on 2020-04-02
 */
@Entity
@Getter
public class Tenant extends EntityBase<Tenant> implements Serializable {

    @NaturalId
    @Column(nullable = false, unique = true)
    private String code;
    private String nick;
    private String secret;

    private boolean alive;
    private String remark;

    private long createTime;
    private long modifyTime;

    public Tenant() {
    }

    public Tenant(String code, String nick, String secret, String remark) {
        this.code = code;
        this.nick = nick;
        this.secret = secret;
        this.remark = remark;

        this.alive = true;
        this.createTime = System.currentTimeMillis();
        this.modifyTime = System.currentTimeMillis();

        //todo 应该在内部转化扩展参数，否则转换成json时没有对应数据。 当然也可以直接返回一个json构造
    }


    @Getter(AccessLevel.NONE)
    @OneToMany(orphanRemoval = true, mappedBy = "tenant", cascade = CascadeType.ALL)
    private List<TenantConfig> configs = new ArrayList<>();

    public boolean sensitiveFlag() {
        //todo 不知道这样判断对不对， 应该是对的就是了 ， 其实boolean类型的参数可以直接定义在domain中
        return configs.stream().anyMatch(config -> config.getConfigKey().equals(KEY_SENSITIVE) && config.getConfigValue().equals("1"));
    }

    public boolean forgeTemplateFlag() {
        return configs.stream().anyMatch(config -> config.getConfigKey().equals(KEY_FORGE_TEMPLATE) && config.getConfigValue().equals("1"));
    }

    public void sensitiveFlag(boolean flag) {
        Optional<TenantConfig> optional = configs.stream().filter(config -> config.getConfigValue().equals(KEY_SENSITIVE)).findFirst();
        if (optional.isPresent()) {
            TenantConfig config = optional.get();
            config.setConfigValue(flag ? "1" : "0");
        } else {
            TenantConfig config = new TenantConfig();
            config.setConfigKey(KEY_SENSITIVE);
            config.setConfigValue(flag ? "1" : "0");
            //todo jpa的一对多关联是在many的一段维护，所以这里要加入 this 才会关联code字段
            config.setTenant(this);
            configs.add(config);
        }
    }

    public void forgeTemplateFlag(boolean flag) {
        Optional<TenantConfig> optional = configs.stream().filter(config -> config.getConfigValue().equals(KEY_FORGE_TEMPLATE)).findFirst();
        if (optional.isPresent()) {
            TenantConfig config = optional.get();
            config.setConfigValue(flag ? "1" : "0");
        } else {
            TenantConfig config = new TenantConfig();
            config.setConfigKey(KEY_FORGE_TEMPLATE);
            config.setConfigValue(flag ? "1" : "0");
            config.setTenant(this);
            configs.add(config);
        }
    }


}
