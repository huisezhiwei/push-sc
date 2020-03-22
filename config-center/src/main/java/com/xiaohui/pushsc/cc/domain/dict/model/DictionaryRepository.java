package com.xiaohui.pushsc.cc.domain.dict.model;

import java.util.List;
import java.util.Optional;

/**
 * @author xiaohui
 * create on 2020-03-15
 */
public interface DictionaryRepository {

    Dictionary save(Dictionary domain);

    void delete(Dictionary domain);

    Optional<Dictionary> find(Long id);

    List<Dictionary> findSet(String dictCode);

    List<Dictionary> findByParentId(Long id);


}
