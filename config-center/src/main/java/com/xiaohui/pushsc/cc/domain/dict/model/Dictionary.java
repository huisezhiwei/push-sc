package com.xiaohui.pushsc.cc.domain.dict.model;

import com.xiaohui.pushsc.cc.domain.base.EntityBase;
import com.xiaohui.pushsc.protocol.source.DictionaryResource;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@NoArgsConstructor
@Getter
@Configurable
public class Dictionary extends EntityBase<Dictionary> {

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

    private String scope;

    private int sort;
    private boolean active;


    /**
     * 父节点标识
     */
    private Long parentId;

    public Dictionary(DictionaryResource resource) {
        this.id = resource.id;
        this.dictCode = resource.code;
        this.dictKey = resource.key;
        this.dictValue = resource.value;
        this.scope = resource.scope;
        this.sort = resource.sort;
        this.active = resource.active;
    }

    public List<Dictionary> subDictionarySet() {
        return dictionaryJPARepository.findByParentId(id);
    }

    public Optional<Dictionary> parentDictionary() {
        return dictionaryJPARepository.find(parentId);
    }

    public DictionaryResource toResource() {

        List<Long> subIds = subDictionarySet().stream().map(Dictionary::getId).collect(Collectors.toList());

        return new DictionaryResource(id, dictCode, dictKey, dictValue, scope, sort, active, subIds);

    }
}
