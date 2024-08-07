package org.wso2.custom.userstore.manager.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.user.core.service.RealmService;
import org.wso2.custom.userstore.manager.CustomUserStoreManager;

@Component(name = "org.wso2.custom.userstore.manager",
        immediate = true)
public class CustomUserStoreManagerServiceComponent {
    private static final Log log = LogFactory.getLog(CustomUserStoreManagerServiceComponent.class);
    private static RealmService realmService;

    @Activate
    protected void activate(ComponentContext ctxt) {

        UserStoreManager customUserStoreManager = new CustomUserStoreManager();
        ctxt.getBundleContext().registerService(UserStoreManager.class.getName(),
                customUserStoreManager, null);
        log.info("CustomUserStoreManager bundle activated successfully..");
    }

    @Deactivate
    protected void deactivate(ComponentContext ctxt) {

        if (log.isDebugEnabled()) {
            log.debug("Custom User Store Manager is deactivated ");
        }
    }

    @Reference(
            name = "RealmService",
            service = org.wso2.carbon.user.core.service.RealmService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetRealmService")

    protected void setRealmService(RealmService rlmService) {

        realmService = rlmService;
    }

    protected void unsetRealmService(RealmService realmService) {

        realmService = null;
    }
}