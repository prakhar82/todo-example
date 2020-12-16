package com.boc.listener;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.persistence.listener.spec.v1.saveconfigdata.SaveConfigDataListener;
import com.backbase.persistence.rest.spec.v1.saveconfigdata.SaveConfigDataPostRequestBody;
import com.backbase.persistence.rest.spec.v1.saveconfigdata.SaveConfigDataPostResponseBody;
import com.boc.persistence.service.SaveConfigDataService;
import com.boc.util.PersistenceUtil;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequestListener
public class SaveConfigDataListenerImpl implements SaveConfigDataListener {

    @Autowired
    SaveConfigDataService saveConfigDataService;

    @Override
    public RequestProxyWrapper<SaveConfigDataPostResponseBody> postSaveConfigData(RequestProxyWrapper<SaveConfigDataPostRequestBody> saveConfigDataPostRequestBody,
                                                                                  Exchange exchange) {
        return PersistenceUtil.buildRequestProxyWrapper(saveConfigDataService.saveConfigData(saveConfigDataPostRequestBody.getRequest().getData()), "POST");
    }
}
