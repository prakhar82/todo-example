package com.boc.listener;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.presentation.errors.*;
import com.backbase.persistence.listener.spec.v1.getconfigdata.ConfigDataListener;
import com.backbase.persistence.rest.spec.v1.getconfigdata.BocCustomErrorException;
import com.backbase.persistence.rest.spec.v1.getconfigdata.ConfigDataByCreditUnionIdGetResponseBody;
import com.backbase.persistence.rest.spec.v1.getconfigdata.UnauthorizedException;
import com.boc.persistence.service.GetConfigDataService;
import com.boc.util.PersistenceUtil;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequestListener
public class GetConfigDataListenerImpl implements ConfigDataListener {

    @Autowired
    GetConfigDataService getConfigDataService;

    @Override
    public RequestProxyWrapper<ConfigDataByCreditUnionIdGetResponseBody> getConfigDataByCreditUnionId(RequestProxyWrapper<Void> request, Exchange exchange, String creditUnionId) throws BadRequestException, ForbiddenException, InternalServerErrorException, NotAcceptableException, NotFoundException, UnsupportedMediaTypeException, BocCustomErrorException, UnauthorizedException {
        return PersistenceUtil.buildRequestProxyWrapper(getConfigDataService.getConfigData(creditUnionId),"GET");
    }
}
