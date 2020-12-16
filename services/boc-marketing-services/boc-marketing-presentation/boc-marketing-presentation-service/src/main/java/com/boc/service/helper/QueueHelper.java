package com.boc.service.helper;

import com.backbase.authentication.listener.client.v1.authentication.AuthenticationAuthenticationClient;
import com.backbase.authentication.rest.spec.v1.authentication.AuthenticationPostRequestBody;
import com.backbase.authentication.rest.spec.v1.authentication.AuthenticationPostResponseBody;
import com.backbase.buildingblocks.backend.api.IDUtils;
import com.backbase.buildingblocks.backend.internalrequest.DefaultInternalRequestContext;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequestContext;
import com.backbase.integration.listener.client.v1.marketingIntegration.IntegrationMarketingWidgetIntegrationClient;
import com.backbase.integration.rest.spec.v1.marketingIntegration.MarketingWidgetIntegrationPatchRequestBody;
import com.backbase.integration.rest.spec.v1.marketingIntegration.MarketingWidgetIntegrationPostRequestBody;
import com.backbase.integration.rest.spec.v1.marketingIntegration.MarketingWidgetIntegrationPostResponseBody;
import com.backbase.persistence.listener.client.v1.apiinfo.PersistenceApiInfoClient;
import com.backbase.persistence.listener.client.v1.authinfo.PersistenceApiAuthInfoClient;
import com.backbase.persistence.listener.client.v1.creditunion.PersistenceCreditUnionClient;
import com.backbase.persistence.listener.client.v1.leadsopportunities.PersistenceLeadsAndOpportunitiesClient;
import com.backbase.persistence.listener.client.v1.saveaudit.PersistenceSaveAuditClient;
import com.backbase.persistence.listener.client.v1.savetoken.PersistenceSaveTokenClient;
import com.backbase.persistence.rest.spec.v1.apiinfo.ApiInfoGetResponseBody;
import com.backbase.persistence.rest.spec.v1.authinfo.ApiAuthInfoGetResponseBody;
import com.backbase.persistence.rest.spec.v1.creditunion.GetCreditUnionByIdGetResponseBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.LeadOpportunityIdPutRequestBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.LeadsAndOpportunitiesGetResponseBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.MemberLeadsOpportunitiesPostRequestBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.MemberLeadsOpportunitiesPostResponseBody;
import com.backbase.persistence.rest.spec.v1.saveaudit.SaveAuditPostRequestBody;
import com.backbase.persistence.rest.spec.v1.savetoken.SaveTokenPostRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class QueueHelper {
    @Autowired
    PersistenceCreditUnionClient persistenceCreditUnionClient;

    @Autowired
    PersistenceLeadsAndOpportunitiesClient persistenceLeadsAndOpportunitiesClient;
    @Autowired
    PersistenceApiInfoClient persistenceApiInfoClient;
    @Autowired
    IntegrationMarketingWidgetIntegrationClient integrationMarketingWidgetIntegrationClient;
    @Autowired
    PersistenceSaveAuditClient persistenceSaveAuditClient;
    @Autowired
    AuthenticationAuthenticationClient authenticationAuthenticationClient;
    @Autowired
    PersistenceApiAuthInfoClient persistenceApiAuthInfoClient;
    @Autowired
    PersistenceSaveTokenClient persistenceSaveTokenClient;

    @Autowired
    IDUtils idUtils;

    public GetCreditUnionByIdGetResponseBody getGetCreditUnionById(String creditUnionId, HttpServletRequest httpServletRequest) {
        return persistenceCreditUnionClient.getGetCreditUnionById(createInternalRequest(null, httpServletRequest),
                creditUnionId).getRequest().getData();
    }

    public <T> InternalRequest<T> createInternalRequest(T data, HttpServletRequest httpServletRequest) {

        InternalRequestContext internalRequestContext = DefaultInternalRequestContext.contextFrom(httpServletRequest, idUtils.generateRandomID());
        InternalRequest<T> internalRequest = new InternalRequest<>();
        internalRequest.setInternalRequestContext(internalRequestContext);
        internalRequest.setData(data);
        return internalRequest;
    }

    public List<MemberLeadsOpportunitiesPostResponseBody> getLeadsAndOpportunities(String creditUnionId, HttpServletRequest httpServletRequest) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String memberNumber = userDetails.getUsername();
        MemberLeadsOpportunitiesPostRequestBody memberPostRequestBody = new MemberLeadsOpportunitiesPostRequestBody();
        memberPostRequestBody.setCreditUnionId(creditUnionId);
        memberPostRequestBody.setMemberNumber(memberNumber);

        return persistenceLeadsAndOpportunitiesClient.postMemberLeadsOpportunities(createInternalRequest(memberPostRequestBody, httpServletRequest)).getRequest().getData();
    }

    public ApiInfoGetResponseBody getApiInfo(String creditUnionId, HttpServletRequest httpServletRequest, String operation) {
        return persistenceApiInfoClient.getApiInfo(createInternalRequest(null, httpServletRequest), creditUnionId, operation).getRequest().getData();
    }

    public MarketingWidgetIntegrationPostResponseBody postMarketingWidgetIntegration(MarketingWidgetIntegrationPostRequestBody marketingWidgetIntegrationPostRequestBody, HttpServletRequest httpServletRequest) {
        return integrationMarketingWidgetIntegrationClient.postMarketingWidgetIntegration(createInternalRequest(marketingWidgetIntegrationPostRequestBody,
                httpServletRequest)).getRequest().getData();
    }

    public void postSaveAudit(SaveAuditPostRequestBody saveAuditPostRequestBody, HttpServletRequest httpServletRequest) {
        persistenceSaveAuditClient.postSaveAudit(createInternalRequest(saveAuditPostRequestBody, httpServletRequest));
    }

    public AuthenticationPostResponseBody postAuthentication(AuthenticationPostRequestBody authenticationPostRequestBody, HttpServletRequest httpServletRequest) {
        return authenticationAuthenticationClient.postAuthentication(createInternalRequest(authenticationPostRequestBody,
                httpServletRequest)).getRequest().getData();
    }

    public ApiAuthInfoGetResponseBody getApiAuthInfo(String creditUnionId, HttpServletRequest httpServletRequest) {
        return persistenceApiAuthInfoClient.getApiAuthInfo(createInternalRequest(null, httpServletRequest), creditUnionId).getRequest().getData();
    }

    public void postSaveToken(SaveTokenPostRequestBody saveTokenPostRequestBody, HttpServletRequest httpServletRequest) {
        persistenceSaveTokenClient.postSaveToken(createInternalRequest(saveTokenPostRequestBody, httpServletRequest)).getRequest().getData();
    }

    public void putLeadOpportunityId(LeadOpportunityIdPutRequestBody leadOpportunityIdPutRequestBody, String leadOpportunityId, HttpServletRequest httpServletRequest) {
        persistenceLeadsAndOpportunitiesClient.putLeadOpportunityId(createInternalRequest(leadOpportunityIdPutRequestBody, httpServletRequest),
                leadOpportunityId).getRequest().getData();
    }

    public void patchMarketingWidgetIntegration(MarketingWidgetIntegrationPatchRequestBody marketingWidgetIntegrationPatchRequestBody, HttpServletRequest httpServletRequest) {
        integrationMarketingWidgetIntegrationClient.patchMarketingWidgetIntegration(createInternalRequest(marketingWidgetIntegrationPatchRequestBody,
                httpServletRequest));
    }
}
