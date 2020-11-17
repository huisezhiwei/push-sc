package com.xiaohui.pushsc.protocol.source;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

/**
 * @author xiaohui
 * create on 2020-03-16
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryResource {

    private Long id;
    private String code;
    private String key;
    private String value;

    private String scope;
    private Integer sort;
    private Boolean active;
    private Long parentId;
    private List<Long> subIds;
}
