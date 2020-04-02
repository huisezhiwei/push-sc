package com.xiaohui.pushsc.source.inframstructure.persist.jpa;

import com.xiaohui.pushsc.source.domain.tenant.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaohui
 * create on 2020-04-02
 */
public interface TenantJPARepository extends JpaRepository<Tenant, Long> {


}
