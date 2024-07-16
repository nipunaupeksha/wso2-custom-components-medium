package org.wso2.custom.userstore.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.UserStoreManager;
import org.wso2.carbon.user.core.common.AbstractUserOperationEventListener;

public class CustomUserStoreListener extends AbstractUserOperationEventListener {

    private static final Log log = LogFactory.getLog(CustomUserStoreListener.class);

    @Override
    public int getExecutionOrderId() {
        return 9000;
    }

    @Override
    public boolean doPostAuthenticate(String userName, boolean authenticated, UserStoreManager userStoreManager)
            throws UserStoreException {

        // check whether user is authenticated
        if(authenticated){

            log.info("=== doPostAuthenticate ===");
            log.info("User " + userName + " logged in at " + System.currentTimeMillis());
            log.info("=== /doPostAuthenticate ===");

        }

        return true;
    }
}
