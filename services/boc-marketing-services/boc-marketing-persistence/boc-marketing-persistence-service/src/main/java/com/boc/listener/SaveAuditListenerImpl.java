package com.boc.listener;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.persistence.listener.spec.v1.saveaudit.SaveAuditListener;
import com.backbase.persistence.rest.spec.v1.saveaudit.SaveAuditPostRequestBody;
import com.backbase.persistence.rest.spec.v1.saveaudit.SaveAuditPostResponseBody;
import com.boc.persistence.service.SaveAuditService;
import com.boc.util.PersistenceUtil;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequestListener
public class SaveAuditListenerImpl implements SaveAuditListener {

    @Autowired
    SaveAuditService creditUnionAuditInfoService;

    @Override
    public RequestProxyWrapper<SaveAuditPostResponseBody> postSaveAudit(RequestProxyWrapper<SaveAuditPostRequestBody> saveAuditPostRequestBody, Exchange exchange) {
        return PersistenceUtil.buildRequestProxyWrapper(creditUnionAuditInfoService.postSaveAudit(saveAuditPostRequestBody.getRequest().getData()), "POST");
    }
}

