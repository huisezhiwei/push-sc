package com.xiaohui.pushsc.cc.infranstructure.persist.jpa;

import com.xiaohui.pushsc.cc.domain.dict.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author xiaohui
 * create on 2020-03-15
 */
@RepositoryRestResource(path = "dict")
public interface DictionaryJPARepository extends JpaRepository<Dictionary, Long> {

    List<Dictionary> findByParentId(Long parentId);

    List<Dictionary> findByDictCode(String dictCode);
}
