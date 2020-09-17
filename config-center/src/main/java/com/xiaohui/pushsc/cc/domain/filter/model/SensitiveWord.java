package com.xiaohui.pushsc.cc.domain.filter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

/**
 * 敏感词过滤实体，只采用包含原则。 使用正则配置敏感词不是一个好方案
 *
 * @author xiaohui
 * create on 2020-03-22
 */
@Entity
@Getter
@NoArgsConstructor
public class SensitiveWord extends AbstractPersistable<Long> {

    private String word;
    /**
     * 敏感词的生效范围
     */
    private String scope;


}
