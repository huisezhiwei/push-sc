package com.xiaohui.pushsc.protocol.source;

import lombok.*;

import java.util.List;

/**
 * @author xiaohui
 * create on 2020-03-16
 */
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryResource {

    public Long id;
    public String code;
    public String key;
    public String value;

    public String scope;
    public int sort;
    public boolean active;

    public List<Long> subIds;
}
