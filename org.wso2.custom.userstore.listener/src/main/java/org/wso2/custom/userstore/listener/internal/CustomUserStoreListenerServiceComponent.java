package org.wso2.custom.userstore.listener.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.carbon.user.core.listener.UserOperationEventListener;
import org.wso2.custom.userstore.listener.CustomUserStoreListener;

@Component(
        name = "org.wso2.custom.userstore.listener",
        immediate = true
)
public class CustomUserStoreListenerServiceComponent {
    private static final Log log = LogFactory.getLog(CustomUserStoreListenerServiceComponent.class);

    @Activate
    protected void activate(ComponentContext context) {

        CustomUserStoreListener listener = new CustomUserStoreListener();
        context.getBundleContext().registerService(UserOperationEventListener.class.getName(),
                listener, null);
        log.debug("Custom user store listener activated successfully.");
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        log.debug("Custom user store listener is deactivated");
    }
}