package com.boc.controller;

import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.presentation.rest.spec.v1.leadsopportunities.*;
import com.boc.service.LeadsOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LeadsOpportunityController implements LeadsAndOpportunitiesApi {

    public static final Logger logger = LoggerFactory.getLogger(LeadsOpportunityController.class);
    @Autowired
    LeadsOpportunityService leadsOpportunityService;

    @Override
    public FetchLeadsAndOpportunitiesPostResponseBody postFetchLeadsAndOpportunities(
            @RequestBody FetchLeadsAndOpportunitiesPostRequestBody fetchLeadsAndOpportunitiesPostRequestBody, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        logger.info("DEMO_001: Inside presentation controller to get logged in member's leads and opportunities");

        FetchLeadsAndOpportunitiesPostResponseBody fetchLeadsAndOpportunitiesPostResponseBody = null;

        try {
            fetchLeadsAndOpportunitiesPostResponseBody = leadsOpportunityService.getLeadsAndOpportunities(fetchLeadsAndOpportunitiesPostRequestBody, httpServletRequest);
        } catch (Exception e) {
            BocCustomErrorException bocCustomErrorException= new BocCustomErrorException();
            bocCustomErrorException.setMessage("Sorry something went wrong. Please try after some time");
            bocCustomErrorException.setCode("301");
            throw bocCustomErrorException;
        }
        return fetchLeadsAndOpportunitiesPostResponseBody;
    }

    @Override
    public void patchUpdateMemberResponse(@RequestBody UpdateMemberResponsePatchRequestBody updateMemberResponsePatchRequestBody, @PathVariable(name = "Id") String id,
                                          HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        logger.info("DEMO_001: Inside presentation controller to update the logged in member's leads and opportunities data");

        try {
            leadsOpportunityService.updateLeadsAndOpportunityMemberResponse(updateMemberResponsePatchRequestBody, id, httpServletRequest);
        } catch (Exception e) {
            BocCustomErrorException bocCustomErrorException= new BocCustomErrorException();
            bocCustomErrorException.setMessage("Sorry something went wrong. Please try after some time");
            bocCustomErrorException.setCode("301");
            throw bocCustomErrorException;
        }

    }
}
