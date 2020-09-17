package com.xiaohui.pushsc.cc.infranstructure.persist.jpa;

import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.business.BusinessSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author xiaohui
 * @date 2020-09-16
 */
@RepositoryRestResource(path = "business")
public interface BusinessSystemJPARepository extends JpaRepository<BusinessSystemEntity, Long> {

}
