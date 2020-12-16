package com.boc.persistence.service;

import com.backbase.persistence.rest.spec.v1.leadsopportunities.*;
import com.boc.persistence.model.CreditUnionLeadOpportunity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CULeadOpportunityService {
    List<LeadsAndOpportunitiesGetResponseBody> getLeadsOpportunities(String creditUnionId);
    List<LeadsAndOpportunitiesGetResponseBody> getActionedLeadsOpportunities(String creditUnionId);
    LeadsAndOpportunitiesPostResponseBody saveLeadsOpportunities(LeadsAndOpportunitiesPostRequestBody lstLeadsAndOpportunitiesPostRequestBody);
    LeadOpportunityIdPutResponseBody updateLeadsOpportunities(LeadOpportunityIdPutRequestBody leadOpportunityIdPutRequestBody, String leadsOpportunityId);
    List<CreditUnionLeadOpportunity> removeDuplicateRecords(List<CreditUnionLeadOpportunity> newCreditUnionLeadsOpportunitiesList, Set<String> leadsOpportunityIds);
    List<LeadOpportunityList> getLeadsOpportunitiesByMemberNumber(MemberLeadsOpportunitiesPostRequestBody memberLeadsOpportunitiesPostRequestBody);
}
