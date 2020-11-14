package com.xiaohui.pushsc.cc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

/**
 * @author xiaohui
 * create on 2020-09-23
 */
@Configuration
public class DataRestConfig extends RepositoryRestConfigurerAdapter {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        config.setBasePath("/rest");
        config.getCorsRegistry()
                .addMapping("/rest/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS","PATCH")
                .exposedHeaders("x-auth-token", "x-auth-tenancy")
                .allowCredentials(false).maxAge(3600);

        Metamodel metamodel = entityManagerFactory.getMetamodel();
        for (ManagedType<?> managedType : metamodel.getManagedTypes()) {
            Class<?> javaType = managedType.getJavaType();
            if (javaType.isAnnotationPresent(Entity.class)) {
                config.exposeIdsFor(managedType.getJavaType());
            }
        }

    }

}
