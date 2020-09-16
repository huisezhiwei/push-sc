package com.xiaohui.pushsc.cc.infranstructure.persist.jpa;

import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.imp.ConfigParams;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaohui
 * @date 2020-09-16
 */
public interface ConfigParamsJPARepository extends JpaRepository<ConfigParams, Long> {
}
