package com.xiaohui.pushsc.cc.infranstructure.persist.jpa.business;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author xiaohui
 * @date 2020-09-17
 */
@Component
@RepositoryEventHandler()
public class BusinessSystemPersistListener {

    @HandleBeforeCreate
    public void handlePersonCreate(BusinessSystemEntity domain) {
        domain.replaceConfigParams();
    }

    @HandleBeforeSave
    public void handlePersonSave(BusinessSystemEntity domain) {
        domain.replaceConfigParams();
    }

}
