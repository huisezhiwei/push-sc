package com.xiaohui.pushsc.cc.infranstructure.persist.jpa;

import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.business.ConfigParams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaohui
 * @date 2020-09-16
 */
public interface ConfigParamsJPARepository extends JpaRepository<ConfigParams, Long> {
}
