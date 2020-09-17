package com.xiaohui.pushsc.cc.infranstructure.persist.jpa;

import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.properties.ConfigProperties;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaohui
 * @date 2020-09-17
 */
public interface ConfigPropertiesRepository extends JpaRepository<ConfigProperties, Long> {
}
