package com.boc.listener;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.persistence.listener.spec.v1.getaudit.GetAuditListener;
import com.backbase.persistence.rest.spec.v1.getaudit.GetAuditPostRequestBody;
import com.backbase.persistence.rest.spec.v1.getaudit.GetAuditPostResponseBody;
import com.boc.persistence.service.GetAuditService;
import com.boc.util.PersistenceUtil;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequestListener
public class GetAuditListenerImpl implements GetAuditListener {

    @Autowired
    GetAuditService getAuditService;

    @Override
    public RequestProxyWrapper<List<GetAuditPostResponseBody>> postGetAudit(RequestProxyWrapper<GetAuditPostRequestBody> getAuditPostRequestBody, Exchange exchange) {
        return PersistenceUtil.buildRequestProxyWrapper(getAuditService.getAudit(getAuditPostRequestBody.getRequest().getData()),"POST");
    }
}

