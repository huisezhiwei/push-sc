package com.xiaohui.pushsc.cc.domain.dict.model;

import com.xiaohui.pushsc.cc.infranstructure.persist.base.EntityModel;
import com.xiaohui.pushsc.protocol.source.DictionaryResource;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 字典表
 *
 * @author xiaohui
 * create on 2020-03-14
 */
@Entity
@Getter
@NoArgsConstructor
@Configurable
public class Dictionary extends EntityModel<Long> {

    @Autowired
    @Getter(AccessLevel.NONE)
    private transient DictionaryRepository dictionaryJPARepository;

    /**
     * 字典码
     */
    @Column(nullable = false)
    private String dictCode;
    @Column(nullable = false)
    private String dictKey;
    @Column(nullable = false)
    private String dictValue;

    private String scope = "system";

    private Integer sort = 0;
    private Boolean active = true;


    /**
     * 父节点标识
     */
    private Long parentId = 0L;

    public Dictionary(DictionaryResource resource) {

        if (resource.getId() == null) {
            create(resource);
        } else {
            update(resource);
        }
    }

    private void update(DictionaryResource resource) {
        setId(resource.getId());
        this.dictCode = resource.getCode();
        this.dictKey = resource.getKey();
        this.dictValue = resource.getValue();
        this.scope = resource.getScope();
        this.sort = resource.getSort();
        this.active = resource.getActive();
        this.parentId = resource.getParentId();
    }

    private void create(DictionaryResource resource) {
        this.dictCode = resource.getCode();
        this.dictKey = resource.getKey();
        this.dictValue = resource.getValue();

        if (resource.getScope() != null) {
            this.scope = resource.getScope();
        }
        if (resource.getSort() != null) {
            this.sort = resource.getSort();
        }
        if (resource.getActive() != null) {
            this.active = resource.getActive();
        }
        if (resource.getParentId() != null) {
            this.parentId = resource.getParentId();
        }
    }

    public List<Dictionary> subDictionarySet() {
        List<Dictionary> subSet = dictionaryJPARepository.findByParentId(getId());
        if (subSet != null) {
            return subSet;
        }
        return new ArrayList<>();
    }

    public Optional<Dictionary> parentDictionary() {
        return dictionaryJPARepository.find(parentId);
    }

    public DictionaryResource toResource() {

        List<Long> subIds = subDictionarySet().stream().map(Dictionary::getId).collect(Collectors.toList());

        return new DictionaryResource(getId(), dictCode, dictKey, dictValue, scope, sort, active, parentId, subIds);

    }
}
