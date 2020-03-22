package com.xiaohui.pushsc.cc.infranstructure.persist.jpa;

import com.xiaohui.pushsc.cc.domain.filter.model.SensitiveWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author xiaohui
 * create on 2020-03-22
 */
@RepositoryRestResource(path = "sensitive_word")
public interface SensitiveWordJPARepository extends JpaRepository<SensitiveWord, Long> {
}
