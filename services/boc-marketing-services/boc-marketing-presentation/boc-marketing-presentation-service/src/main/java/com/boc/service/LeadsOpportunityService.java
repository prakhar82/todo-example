package com.boc.service;

import com.backbase.presentation.rest.spec.v1.leadsopportunities.FetchLeadsAndOpportunitiesPostRequestBody;
import com.backbase.presentation.rest.spec.v1.leadsopportunities.FetchLeadsAndOpportunitiesPostResponseBody;
import com.backbase.presentation.rest.spec.v1.leadsopportunities.UpdateMemberResponsePatchRequestBody;

import javax.servlet.http.HttpServletRequest;

public interface LeadsOpportunityService {

    FetchLeadsAndOpportunitiesPostResponseBody getLeadsAndOpportunities(FetchLeadsAndOpportunitiesPostRequestBody leadsAndOpportunitiesPostRequestBody, HttpServletRequest httpServletRequest);
    void updateLeadsAndOpportunityMemberResponse(UpdateMemberResponsePatchRequestBody updateMemberResponsePatchRequestBody, String id, HttpServletRequest httpServletRequest);
}
