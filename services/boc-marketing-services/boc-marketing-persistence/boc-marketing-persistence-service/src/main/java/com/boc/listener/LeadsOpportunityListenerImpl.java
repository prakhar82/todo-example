package com.boc.listener;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.buildingblocks.presentation.errors.*;
import com.backbase.persistence.listener.spec.v1.leadsopportunities.LeadsAndOpportunitiesListener;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.*;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.UnauthorizedException;
import com.boc.persistence.service.impl.CULeadOpportunityServiceImpl;
import com.boc.util.PersistenceUtil;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequestListener
public class LeadsOpportunityListenerImpl implements LeadsAndOpportunitiesListener {
    public static final Logger logger = LoggerFactory.getLogger(LeadsOpportunityListenerImpl.class);

    @Autowired
    CULeadOpportunityServiceImpl cuLeadOpportunityService;

    @Override
    public RequestProxyWrapper<List<LeadsAndOpportunitiesGetResponseBody>> getLeadsAndOpportunities(RequestProxyWrapper<Void> request, Exchange exchange, String creditUnionId, String fetchAll) throws BadRequestException, ForbiddenException, InternalServerErrorException, NotAcceptableException, NotFoundException, UnsupportedMediaTypeException, BocCustomErrorException, UnauthorizedException {
        logger.info("DEMO_001: Inside persistence listener, fetching leads and opportunities from the Database");
        if(fetchAll.equalsIgnoreCase("yes")){
            return PersistenceUtil.buildRequestProxyWrapper(cuLeadOpportunityService.getLeadsOpportunities(creditUnionId), "GET");
        }
        return PersistenceUtil.buildRequestProxyWrapper(cuLeadOpportunityService.getActionedLeadsOpportunities(creditUnionId), "GET");
    }


    @Override
    public RequestProxyWrapper<LeadsAndOpportunitiesPostResponseBody> postLeadsAndOpportunities(RequestProxyWrapper<LeadsAndOpportunitiesPostRequestBody> leadsAndOpportunitiesPostRequestBody, Exchange exchange) throws BadRequestException, ForbiddenException, InternalServerErrorException, NotAcceptableException, NotFoundException, UnsupportedMediaTypeException, BocCustomErrorException, UnauthorizedException {
        logger.info("DEMO_001: Inside persistence listener to add leads and opportunities in to the Database");
        LeadsAndOpportunitiesPostResponseBody leadsAndOpportunitiesPostResponseBody = cuLeadOpportunityService.saveLeadsOpportunities(leadsAndOpportunitiesPostRequestBody.getRequest().getData());
        return PersistenceUtil.buildRequestProxyWrapper(leadsAndOpportunitiesPostResponseBody,
                "POST");
    }

    @Override
    public RequestProxyWrapper<LeadOpportunityIdPutResponseBody> putLeadOpportunityId(RequestProxyWrapper<LeadOpportunityIdPutRequestBody> leadOpportunityIdPutRequestBody,
                                                                                      Exchange exchange, String leadOpportunityId) {
        logger.info("DEMO_001: Inside persistence listener to update lead and opportunity in the Database");
        return PersistenceUtil.buildRequestProxyWrapper(cuLeadOpportunityService.updateLeadsOpportunities(leadOpportunityIdPutRequestBody.getRequest().getData(),
                leadOpportunityId),
                "PUT");
    }

    @Override
    public RequestProxyWrapper<List<LeadOpportunityList>> postMemberLeadsOpportunities(RequestProxyWrapper<MemberLeadsOpportunitiesPostRequestBody> memberLeadsOpportunitiesPostRequestBody, Exchange exchange) throws BadRequestException, ForbiddenException, InternalServerErrorException, NotAcceptableException, NotFoundException, UnsupportedMediaTypeException, BocCustomErrorException, UnauthorizedException {
        return PersistenceUtil.buildRequestProxyWrapper(cuLeadOpportunityService.getLeadsOpportunitiesByMemberNumber(memberLeadsOpportunitiesPostRequestBody.getRequest().getData()
                ),
                "POST");
    }


    /*@Override
    public RequestProxyWrapper<List<LeadsAndOpportunitiesGetResponseBody>> postMemberLeadsOpportunities(RequestProxyWrapper<MemberLeadsOpportunitiesPostRequestBody> memberLeadsOpportunitiesPostRequestBody, Exchange exchange) throws BadRequestException, ForbiddenException, InternalServerErrorException, NotAcceptableException, NotFoundException, UnsupportedMediaTypeException, BocCustomErrorException, UnauthorizedException {

    }*/
}
