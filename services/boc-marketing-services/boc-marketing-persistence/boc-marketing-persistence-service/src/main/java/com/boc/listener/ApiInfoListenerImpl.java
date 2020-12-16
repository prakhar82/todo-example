package com.boc.listener;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.persistence.listener.spec.v1.apiinfo.ApiInfoListener;
import com.backbase.persistence.rest.spec.v1.apiinfo.ApiInfoGetResponseBody;
import com.boc.persistence.service.CreditUnionApiInfoService;
import com.boc.util.PersistenceUtil;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequestListener
public class ApiInfoListenerImpl implements ApiInfoListener {
    private final Logger logger = LoggerFactory.getLogger(ApiInfoListenerImpl.class);

    @Autowired
    CreditUnionApiInfoService creditUnionApiInfoService;

    @Override
    public RequestProxyWrapper<ApiInfoGetResponseBody> getApiInfo(RequestProxyWrapper<Void> request, Exchange exchange, String creditUnionId,
                                                                  String operation) {

        logger.info("Inside Listener to get API Info");
        ApiInfoGetResponseBody apiInfoGetResponseBody = creditUnionApiInfoService.getCreditUnionApiInfo(creditUnionId, operation);
        return PersistenceUtil.buildRequestProxyWrapper(apiInfoGetResponseBody, "GET");
    }


}
