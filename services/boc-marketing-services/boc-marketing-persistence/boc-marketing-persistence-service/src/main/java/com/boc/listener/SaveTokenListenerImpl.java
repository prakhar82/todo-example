package com.boc.listener;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.persistence.listener.spec.v1.savetoken.SaveTokenListener;
import com.backbase.persistence.rest.spec.v1.savetoken.SaveTokenPostRequestBody;
import com.backbase.persistence.rest.spec.v1.savetoken.SaveTokenPostResponseBody;
import com.boc.persistence.service.SaveTokenService;
import com.boc.util.PersistenceUtil;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequestListener
public class SaveTokenListenerImpl implements SaveTokenListener {

    @Autowired
    SaveTokenService saveTokenService;

    @Override
    public RequestProxyWrapper<SaveTokenPostResponseBody> postSaveToken(RequestProxyWrapper<SaveTokenPostRequestBody> saveTokenPostRequestBody, Exchange exchange) {
        return PersistenceUtil.buildRequestProxyWrapper(saveTokenService.saveToken(saveTokenPostRequestBody.getRequest().getData()), "POST");
    }
}
