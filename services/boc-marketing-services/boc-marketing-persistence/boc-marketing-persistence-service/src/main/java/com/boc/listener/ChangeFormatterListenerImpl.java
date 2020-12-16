package com.boc.listener;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.buildingblocks.presentation.errors.*;
import com.backbase.persistence.listener.spec.v1.changeFormatter.ChangeFormatterListener;
import com.backbase.persistence.rest.spec.v1.changeFormatter.BocCustomErrorException;
import com.backbase.persistence.rest.spec.v1.changeFormatter.ChangeFormatterPostRequestBody;
import com.backbase.persistence.rest.spec.v1.changeFormatter.ChangeFormatterPostResponseBody;
import com.backbase.persistence.rest.spec.v1.changeFormatter.UnauthorizedException;
import com.boc.persistence.service.ChangeFormatterService;
import com.boc.util.PersistenceUtil;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequestListener
public class ChangeFormatterListenerImpl implements ChangeFormatterListener {

    @Autowired
    ChangeFormatterService changeFormatterService;

    public static final Logger logger = LoggerFactory.getLogger(LeadsOpportunityListenerImpl.class);

    @Override
    public RequestProxyWrapper<ChangeFormatterPostResponseBody> postChangeFormatter(RequestProxyWrapper<ChangeFormatterPostRequestBody> changeFormatterPostRequestBody,
                                                                                    Exchange exchange) throws BadRequestException, ForbiddenException,
            InternalServerErrorException, NotAcceptableException, NotFoundException, UnsupportedMediaTypeException, BocCustomErrorException, UnauthorizedException {

        logger.info("DEMO_001: Inside persistence listener to change the Data Formatter");
        return PersistenceUtil.buildRequestProxyWrapper(changeFormatterService.changeFormatter(changeFormatterPostRequestBody.getRequest().getData()),"POST");
    }
}
