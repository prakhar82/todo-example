package com.boc.listener;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.persistence.listener.spec.v1.creditunion.CreditUnionListener;
import com.backbase.persistence.rest.spec.v1.creditunion.GetCreditUnionByIdGetResponseBody;
import com.boc.persistence.service.CreditUnionService;
import com.boc.util.PersistenceUtil;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequestListener
public class CreditUnionInfoListenerImpl implements CreditUnionListener {

    @Autowired
    CreditUnionService creditUnionService;

    @Override
    public RequestProxyWrapper<GetCreditUnionByIdGetResponseBody> getGetCreditUnionById(RequestProxyWrapper<Void> request, Exchange exchange,
                                                                                        String creditUnionId) {
        return PersistenceUtil.buildRequestProxyWrapper(creditUnionService.getCreditUnionById(creditUnionId), "GET");
    }
}
