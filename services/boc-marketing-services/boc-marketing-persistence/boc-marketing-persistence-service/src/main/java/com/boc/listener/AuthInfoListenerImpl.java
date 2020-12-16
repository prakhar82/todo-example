package com.boc.listener;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.persistence.listener.spec.v1.authinfo.ApiAuthInfoListener;
import com.backbase.persistence.rest.spec.v1.authinfo.ApiAuthInfoGetResponseBody;
import com.boc.persistence.service.CreditUnionAppAuthService;
import com.boc.util.PersistenceUtil;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequestListener
public class AuthInfoListenerImpl implements ApiAuthInfoListener {

    public static final Logger logger = LoggerFactory.getLogger(AuthInfoListenerImpl.class);
    @Autowired
    CreditUnionAppAuthService creditUnionAppAuthService;

    @Override
    public RequestProxyWrapper<ApiAuthInfoGetResponseBody> getApiAuthInfo(RequestProxyWrapper<Void> request, Exchange exchange,
                                                                          String creditUnionId) {
        logger.info("DEMO_001: Inside persistence layer listener to get access token");
        ApiAuthInfoGetResponseBody apiAuthInfoGetResponseBody = creditUnionAppAuthService.getAuthInfo(creditUnionId);
        return PersistenceUtil.buildRequestProxyWrapper(apiAuthInfoGetResponseBody, "GET");
    }
}
