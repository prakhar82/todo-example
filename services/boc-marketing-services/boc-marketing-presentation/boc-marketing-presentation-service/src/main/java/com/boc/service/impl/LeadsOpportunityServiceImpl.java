package com.boc.service.impl;

import com.backbase.authentication.rest.spec.v1.authentication.AuthenticationPostRequestBody;
import com.backbase.authentication.rest.spec.v1.authentication.AuthenticationPostResponseBody;
import com.backbase.buildingblocks.backend.api.IDUtils;
import com.backbase.buildingblocks.backend.internalrequest.DefaultInternalRequestContext;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequestContext;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.integration.rest.spec.v1.marketingIntegration.MarketingWidgetIntegrationPatchRequestBody;
import com.backbase.integration.rest.spec.v1.marketingIntegration.MarketingWidgetIntegrationPostRequestBody;
import com.backbase.integration.rest.spec.v1.marketingIntegration.MarketingWidgetIntegrationPostResponseBody;
import com.backbase.persistence.rest.spec.v1.apiinfo.ApiInfoGetResponseBody;
import com.backbase.persistence.rest.spec.v1.authinfo.ApiAuthInfoGetResponseBody;
import com.backbase.persistence.rest.spec.v1.creditunion.GetCreditUnionByIdGetResponseBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.LeadOpportunityIdPutRequestBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.LeadsAndOpportunitiesGetResponseBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.MemberLeadsOpportunitiesPostResponseBody;
import com.backbase.persistence.rest.spec.v1.saveaudit.SaveAuditPostRequestBody;
import com.backbase.persistence.rest.spec.v1.savetoken.SaveTokenPostRequestBody;
import com.backbase.presentation.rest.spec.v1.leadsopportunities.*;
import com.boc.constants.Constants;
import com.boc.service.LeadsOpportunityService;
import com.boc.service.helper.QueueHelper;
import com.boc.transformer.PresentationTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LeadsOpportunityServiceImpl implements LeadsOpportunityService {

    public static final Logger logger = LoggerFactory.getLogger(LeadsOpportunityServiceImpl.class);

    @Autowired
    IDUtils idUtils;
    @Autowired
    QueueHelper queueHelper;
    @Autowired
    PresentationTransformer presentationTransformer;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public FetchLeadsAndOpportunitiesPostResponseBody getLeadsAndOpportunities(FetchLeadsAndOpportunitiesPostRequestBody fetchLeadsAndOpportunitiesPostRequestBody,
                                                                               HttpServletRequest httpServletRequest) {

        final String txnId = UUID.randomUUID().toString();
        FetchLeadsAndOpportunitiesPostResponseBody fetchLeadsAndOpportunitiesPostResponseBody = null;
        String accessToken;
        GetCreditUnionByIdGetResponseBody getCreditUnionByIdGetResponseBody;
        ApiInfoGetResponseBody apiInfoGetResponseBody;
        String requestJson = null;
        String creditUnionId = null;
        String dataFormatter = httpServletRequest.getHeader(Constants.DATA_FORMATTER);

        try {
            requestJson = objectMapper.writeValueAsString(fetchLeadsAndOpportunitiesPostRequestBody);
            creditUnionId = fetchLeadsAndOpportunitiesPostRequestBody.getCreditUnionId();

            if (creditUnionId == null) {
                throw getCustomExceptionInstance();
            }
            // Get the credit union details. ie, source
            getCreditUnionByIdGetResponseBody = queueHelper.getGetCreditUnionById(creditUnionId, httpServletRequest);

            if (getCreditUnionByIdGetResponseBody == null)
                return presentationTransformer.mapAsFetchLeadsAndOpportunitiesPostResponseBodyDb(null);

            if (getCreditUnionByIdGetResponseBody.getSource().equals(Constants.SOURCE_CSV)) {
                List<MemberLeadsOpportunitiesPostResponseBody> response = queueHelper.getLeadsAndOpportunities(creditUnionId, httpServletRequest);
                fetchLeadsAndOpportunitiesPostResponseBody = mapAsFetchLeadsAndOpportunitiesPostResponseBodyDb(response);

            } else if (getCreditUnionByIdGetResponseBody.getSource().equals(Constants.SOURCE_SALESFORCE)) {
                accessToken = getAccessToken(creditUnionId, httpServletRequest, txnId, null);
                apiInfoGetResponseBody = queueHelper.getApiInfo(creditUnionId, httpServletRequest, Constants.FETCH);

                MarketingWidgetIntegrationPostRequestBody marketingWidgetIntegrationPostRequestBody =
                        presentationTransformer.mapAsMarketingWidgetIntegrationPostRequestBody(accessToken, apiInfoGetResponseBody);
                MarketingWidgetIntegrationPostResponseBody marketingWidgetIntegrationPostResponseBody = queueHelper.postMarketingWidgetIntegration(marketingWidgetIntegrationPostRequestBody, httpServletRequest);

                fetchLeadsAndOpportunitiesPostResponseBody = mapAsFetchLeadsAndOpportunitiesPostResponseBody(marketingWidgetIntegrationPostResponseBody);
            }
        } catch (Exception e) {
            logger.info("Error: " + e.getMessage());
            SaveAuditPostRequestBody saveAuditPostRequestBody = presentationTransformer.mapPresentationAsSaveAuditPostRequestBody(requestJson, creditUnionId,
                    Constants.SALESFORCE_TRANSACTION, e.getMessage(), txnId, null, null, dataFormatter);
            queueHelper.postSaveAudit(saveAuditPostRequestBody, httpServletRequest);
            throw getCustomExceptionInstance();
        }

        return fetchLeadsAndOpportunitiesPostResponseBody;
    }

    public FetchLeadsAndOpportunitiesPostResponseBody mapAsFetchLeadsAndOpportunitiesPostResponseBody(MarketingWidgetIntegrationPostResponseBody marketingWidgetIntegrationPostResponseBody) {
        return presentationTransformer.mapAsFetchLeadsAndOpportunitiesPostResponseBody(marketingWidgetIntegrationPostResponseBody);
    }

    public FetchLeadsAndOpportunitiesPostResponseBody mapAsFetchLeadsAndOpportunitiesPostResponseBodyDb(List<MemberLeadsOpportunitiesPostResponseBody> response) {

        FetchLeadsAndOpportunitiesPostResponseBody fetchLeadsAndOpportunitiesPostResponseBody;
        logger.info("DEMO_001: Inside presentation service layer, this credit union is file based, fetching leads and opportunities from the Database");

        List<Record> recordsDb = presentationTransformer.mapDatabaseLeadsOpportunitiesAsRecords(response);
        fetchLeadsAndOpportunitiesPostResponseBody = presentationTransformer.mapAsFetchLeadsAndOpportunitiesPostResponseBodyDb(recordsDb);
        return fetchLeadsAndOpportunitiesPostResponseBody;
    }

    @Override
    public void updateLeadsAndOpportunityMemberResponse(UpdateMemberResponsePatchRequestBody updateMemberResponsePatchRequestBody, String id,
                                                        HttpServletRequest httpServletRequest) {

        if (updateMemberResponsePatchRequestBody == null) {
            throw getCustomExceptionInstance();
        }

        String dataFormatter = httpServletRequest.getHeader(Constants.DATA_FORMATTER);
        String requestJson = null;
        String accessToken;
        ApiInfoGetResponseBody apiInfoGetResponseBody;
        String txnId = UUID.randomUUID().toString();

        GetCreditUnionByIdGetResponseBody getCreditUnionByIdGetResponseBody;
        String creditUnionId = updateMemberResponsePatchRequestBody.getCreditUnionId();
        MarketingWidgetIntegrationPatchRequestBody marketingWidgetIntegrationPatchRequestBody = null;

        try {
            requestJson = objectMapper.writeValueAsString(updateMemberResponsePatchRequestBody);

            logger.info("inside service:::::::::::::::::::::: requestJson:::::::::: " + requestJson);
            getCreditUnionByIdGetResponseBody = queueHelper.getGetCreditUnionById(creditUnionId, httpServletRequest);


            logger.info("DEMO_001: Inside presentation service layer, Check the credit union is file based or not");
            if (getCreditUnionByIdGetResponseBody.getSource().equals(Constants.SOURCE_CSV)) {
                logger.info("DEMO_001: Inside presentation service layer, this credit union is file based, update lead and opportunity in the Database");
                updateLeadsAndOpportunityMemberResponseDatabase(updateMemberResponsePatchRequestBody, id, httpServletRequest);
            } else if (getCreditUnionByIdGetResponseBody.getSource().equals(Constants.SOURCE_SALESFORCE)) {

                logger.info("DEMO_001: Inside presentation service layer, this credit union is not file based, updating lead and opportunity on the CRM");

                accessToken = getAccessToken(creditUnionId, httpServletRequest, txnId, id);
                apiInfoGetResponseBody = queueHelper.getApiInfo(creditUnionId, httpServletRequest, Constants.UPDATE);
                marketingWidgetIntegrationPatchRequestBody = presentationTransformer.mapAsMarketingWidgetIntegrationPatchRequestBody(updateMemberResponsePatchRequestBody,
                        id, apiInfoGetResponseBody, accessToken);
                queueHelper.patchMarketingWidgetIntegration(marketingWidgetIntegrationPatchRequestBody, httpServletRequest);

                logger.info("DEMO_001: Inside presentation service layer, this credit union is not file based, updating lead and opportunity on the CRM. Successfully " +
                        "called the API.");
            }

        } catch (Exception e) {
            logger.info("***** Exception inside LeadsOpportunityServiceImpl.updateLeadsAndOpportunityMemberResponse() *** : " + e.getMessage());
            SaveAuditPostRequestBody saveAuditPostRequestBody;
            if (marketingWidgetIntegrationPatchRequestBody != null) {
                saveAuditPostRequestBody = presentationTransformer.mapPresentationAsSaveAuditPostRequestBody(requestJson, creditUnionId,
                        Constants.DATABASE_TRANSACTION, e.getMessage(), txnId, id, marketingWidgetIntegrationPatchRequestBody.getRequestBody(), dataFormatter);
            } else {
                saveAuditPostRequestBody = presentationTransformer.mapPresentationAsSaveAuditPostRequestBody(requestJson, creditUnionId,
                        Constants.DATABASE_TRANSACTION, e.getMessage(), txnId, id, null, dataFormatter);
            }
            queueHelper.postSaveAudit(saveAuditPostRequestBody, httpServletRequest);
            throw getCustomExceptionInstance();
        }
    }

    public String getAccessToken(String creditUnionId, HttpServletRequest httpServletRequest, String txnId, String leadsOpportunityId) {
        logger.info("DEMO_001: Inside presentation layer, Getting access token from the database");
        String accessToken;
        ApiAuthInfoGetResponseBody apiAuthInfoGetResponseBody;

        apiAuthInfoGetResponseBody = getAuthenticationInformation(creditUnionId, httpServletRequest, txnId, leadsOpportunityId);

        if (apiAuthInfoGetResponseBody == null) {
            throw getCustomExceptionInstance();
        }

        if (isAccessTokenValid(apiAuthInfoGetResponseBody.getTokenExpiryOn())) {
            logger.info("DEMO_001: Inside presentation layer, got the valid access token from the database");
            return apiAuthInfoGetResponseBody.getAccessToken();
        }
        logger.info("DEMO_001: Inside presentation layer, access token is not valid, calling authentication layer to get new access token");
        accessToken = getAuthenticationAccessToken(httpServletRequest, apiAuthInfoGetResponseBody, txnId, leadsOpportunityId);
        boolean saveStatus = saveAccessToken(creditUnionId, httpServletRequest, accessToken);
        return saveStatus ? accessToken : null;
    }

    public void updateLeadsAndOpportunityMemberResponseDatabase(UpdateMemberResponsePatchRequestBody updateMemberResponsePatchRequestBody, String leadOpportunityId,
                                                                HttpServletRequest httpServletRequest) {
        try {
            LeadOpportunityIdPutRequestBody leadOpportunityIdPutRequestBody = getLeadOpportunityIdPutRequestBody(updateMemberResponsePatchRequestBody);
            queueHelper.putLeadOpportunityId(leadOpportunityIdPutRequestBody, leadOpportunityId, httpServletRequest);
        } catch (Exception e) {
            logger.info("Error on updating:" + e.getMessage());
            throw getCustomExceptionInstance();
        }
    }

    public LeadOpportunityIdPutRequestBody getLeadOpportunityIdPutRequestBody(UpdateMemberResponsePatchRequestBody updateMemberResponsePatchRequestBody) {
        LeadOpportunityIdPutRequestBody leadOpportunityIdPutRequestBody = new LeadOpportunityIdPutRequestBody();
        leadOpportunityIdPutRequestBody.setBocforgeMemberResponseC(updateMemberResponsePatchRequestBody.getBocforgeMemberResponse());
        leadOpportunityIdPutRequestBody.setBocforgeButtonCodeC(updateMemberResponsePatchRequestBody.getBocforgeButtonCode());
        leadOpportunityIdPutRequestBody.setBocforgeChoiceCodeC(updateMemberResponsePatchRequestBody.getBocforgeChoiceCode());
        leadOpportunityIdPutRequestBody.setBocforgeMemberChoiceTextC(updateMemberResponsePatchRequestBody.getBocforgeMemberChoiceText());
        return leadOpportunityIdPutRequestBody;
    }

    public boolean saveAccessToken(String creditUnionId, HttpServletRequest httpServletRequest, String accessToken) {
        logger.info("DEMO_001: Inside presentation layer, saving the new access token into the database");
        SaveTokenPostRequestBody saveTokenPostRequestBody = getSaveTokenPostRequestBody(creditUnionId, accessToken);
        queueHelper.postSaveToken(saveTokenPostRequestBody, httpServletRequest);
        return true;
    }

    public SaveTokenPostRequestBody getSaveTokenPostRequestBody(String creditUnionId, String accessToken) {

        SaveTokenPostRequestBody saveTokenPostRequestBody = new SaveTokenPostRequestBody();
        saveTokenPostRequestBody.setCreditUnionId(creditUnionId);
        saveTokenPostRequestBody.setAccessToken(accessToken);
        return saveTokenPostRequestBody;
    }

    public String getAuthenticationAccessToken(HttpServletRequest httpServletRequest, ApiAuthInfoGetResponseBody apiAuthInfoGetResponseBody, String txnId, String leadsOpportunityId) {

        String requestJson = null;
        String dataFormatter = httpServletRequest.getHeader(Constants.DATA_FORMATTER);

        try {
            requestJson = objectMapper.writeValueAsString(apiAuthInfoGetResponseBody);
            // token is not valid, call authentication service
            AuthenticationPostRequestBody authenticationPostRequestBody = objectMapper.convertValue(apiAuthInfoGetResponseBody, AuthenticationPostRequestBody.class);
            authenticationPostRequestBody.setCreditUnionId(String.valueOf(apiAuthInfoGetResponseBody.getCreditUnionId()));
            // authentication service call via queue.
            AuthenticationPostResponseBody authenticationPostResponseBody = queueHelper.postAuthentication(authenticationPostRequestBody, httpServletRequest);
            return authenticationPostResponseBody.getAccessToken();
        } catch (Exception e) {
            logger.info("Error on authentication service call: " + e.getMessage());
            SaveAuditPostRequestBody saveAuditPostRequestBody = presentationTransformer.mapPresentationAsSaveAuditPostRequestBody(requestJson, apiAuthInfoGetResponseBody.getCreditUnionId()
                    , Constants.AUTHENTICATION, e.getMessage(), txnId, leadsOpportunityId, null, dataFormatter);
            queueHelper.postSaveAudit(saveAuditPostRequestBody, httpServletRequest);
            throw getCustomExceptionInstance().withMessage(e.getMessage());
        }
    }

    public ApiAuthInfoGetResponseBody getAuthenticationInformation(String creditUnionId, HttpServletRequest httpServletRequest, String txnId, String leadsOpportunityId) {
        String dataFormatter = httpServletRequest.getHeader(Constants.DATA_FORMATTER);
        try {
            return queueHelper.getApiAuthInfo(creditUnionId, httpServletRequest);
        } catch (Exception e) {
            logger.info("Error on fetching Auth Info from persistence: " + e.getMessage());
            SaveAuditPostRequestBody saveAuditPostRequestBody = presentationTransformer.mapPresentationAsSaveAuditPostRequestBody(null, creditUnionId,
                    Constants.DATABASE_TRANSACTION, e.getMessage(), txnId, leadsOpportunityId, null, dataFormatter);
            queueHelper.postSaveAudit(saveAuditPostRequestBody, httpServletRequest);
            throw getCustomExceptionInstance();
        }
    }

    public <T> InternalRequest<T> createInternalRequest(T data, HttpServletRequest httpServletRequest) {

        InternalRequestContext internalRequestContext = DefaultInternalRequestContext.contextFrom(httpServletRequest, idUtils.generateRandomID());
        InternalRequest<T> internalRequest = new InternalRequest<>();
        internalRequest.setInternalRequestContext(internalRequestContext);
        internalRequest.setData(data);
        return internalRequest;
    }

    public boolean isAccessTokenValid(String accessTokenExpiryOn) {
        if (accessTokenExpiryOn == null)
            return false;
        Date tokenExpiryOn = new Date(Long.parseLong(accessTokenExpiryOn));
        return (tokenExpiryOn.toInstant().isAfter(Instant.now()));
    }

    public BocCustomErrorException getCustomExceptionInstance() {
        BocCustomErrorException bocCustomErrorException = new BocCustomErrorException();
        bocCustomErrorException.setMessage("Sorry something went wrong. Please try after some time");
        bocCustomErrorException.setCode("301");
        return bocCustomErrorException;
    }
}
