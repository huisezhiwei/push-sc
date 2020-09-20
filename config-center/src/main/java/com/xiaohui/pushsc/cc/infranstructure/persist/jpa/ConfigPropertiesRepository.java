package com.xiaohui.pushsc.cc.infranstructure.persist.jpa;

import com.xiaohui.pushsc.cc.infranstructure.persist.jpa.properties.ConfigProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaohui
 * @date 2020-09-17
 */
public interface ConfigPropertiesRepository extends JpaRepository<ConfigProperties, Long> {

    List<ConfigProperties> findByPropKeyStartsWith(String prefix);

}
