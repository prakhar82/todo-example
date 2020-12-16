package com.boc.integration.listener;

import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.integration.listener.spec.v1.marketingIntegration.MarketingWidgetIntegrationListener;
import com.backbase.integration.rest.spec.v1.marketingIntegration.*;
import com.boc.api.model.CreditUnionResponseSchema;
import com.boc.api.spec.MarketingObjectApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Service
@RequestListener
public class MarketingIntegrationListener implements MarketingWidgetIntegrationListener {

    private final Logger logger = LoggerFactory.getLogger(MarketingIntegrationListener.class);

    @Autowired
    private final MarketingObjectApi marketingObjectApi;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public MarketingIntegrationListener(MarketingObjectApi marketingObjectApi) {
        this.marketingObjectApi = marketingObjectApi;
    }

    @Value("${customObjectFetchQuery}")
    String customObjectFetchQuery;

    @Value("${customObjectUpdateQuery}")
    String customObjectUpdateQuery;

    @Override
    public RequestProxyWrapper<MarketingWidgetIntegrationPostResponseBody> postMarketingWidgetIntegration(
            RequestProxyWrapper<MarketingWidgetIntegrationPostRequestBody> marketingWidgetIntegrationPostRequestBody, Exchange exchange) {

        logger.info("DEMO_001: Inside integration listener to get the logged in member's leads and opportunities data");

        MarketingWidgetIntegrationPostResponseBody marketingWidgetIntegrationPostResponseBody;
        marketingWidgetIntegrationPostResponseBody = parseIntegrationResponse(marketingWidgetIntegrationPostRequestBody.getRequest().getData());
        return buildRequestProxyWrapper(marketingWidgetIntegrationPostResponseBody, "POST");
    }

    @Override
    public RequestProxyWrapper<Void> patchMarketingWidgetIntegration(
            RequestProxyWrapper<MarketingWidgetIntegrationPatchRequestBody> marketingWidgetIntegrationPatchRequestBody, Exchange exchange) {
        logger.info("DEMO_001: Inside integration listener to update the logged in member's leads and opportunity data");
        String authorization = "Bearer " + marketingWidgetIntegrationPatchRequestBody.getRequest().getData().getAccessToken();
        String baseUrl = marketingWidgetIntegrationPatchRequestBody.getRequest().getData().getBaseUrl();
        String leadOpportunityId = marketingWidgetIntegrationPatchRequestBody.getRequest().getData().getId();

        RequestBody requestBody = marketingWidgetIntegrationPatchRequestBody.getRequest().getData().getRequestBody();
        com.boc.api.model.RequestBody requestBodyModel = objectMapper.convertValue(requestBody, com.boc.api.model.RequestBody.class);
        updateLeadsAndOpportunity(baseUrl + customObjectUpdateQuery + "/" + leadOpportunityId, authorization, requestBodyModel);
        return buildRequestProxyWrapper(null, "PATCH");
    }

    private MarketingWidgetIntegrationPostResponseBody parseIntegrationResponse(
            MarketingWidgetIntegrationPostRequestBody marketingWidgetIntegrationPostRequestBody) {

        CreditUnionResponseSchema creditUnionResponseSchema = executeRequest(marketingWidgetIntegrationPostRequestBody);
        return new ObjectMapper().convertValue(creditUnionResponseSchema, MarketingWidgetIntegrationPostResponseBody.class);
    }

    public CreditUnionResponseSchema executeRequest(MarketingWidgetIntegrationPostRequestBody marketingWidgetIntegrationPostRequestBody) {
        try {
            String baseUrl = marketingWidgetIntegrationPostRequestBody.getBaseUrl();

            String accessToken = "Bearer " + marketingWidgetIntegrationPostRequestBody.getAccessToken();
            String memberNumber = marketingWidgetIntegrationPostRequestBody.getMemberNumber();
            marketingObjectApi.getApiClient().setBasePath(baseUrl);
            String queryWithMemberNumber = MessageFormat.format(customObjectFetchQuery, "'" + memberNumber + "'");
            marketingObjectApi.getApiClient().setBasePath(baseUrl);
            return marketingObjectApi.queryGet(queryWithMemberNumber, accessToken);
        } catch (Exception e) {
            logger.info("Looks like the server is down, getting the JSON from the file");
            throw getCustomExceptionInstance().withMessage(e.getMessage());
        }
    }

    public void updateLeadsAndOpportunity(String url, String authorization, com.boc.api.model.RequestBody requestBody) {

        if (url.isEmpty() || authorization.isEmpty()){
            throw getCustomExceptionInstance();
        }

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", authorization);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity;
        try {
            entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), httpHeaders);
        } catch (JsonProcessingException e) {
            throw getCustomExceptionInstance();
        }

        try {
            restTemplate.exchange(url, HttpMethod.PATCH, entity, String.class);
            logger.info("Inside updateLeadsAndOpportunity of Integration layer. Successfully called the API");
        } catch (RestClientException e) {
            logger.info("***** Exception inside MarketingIntegrationListener.updateLeadsAndOpportunity() *** : " +e.getMessage());
            throw getCustomExceptionInstance();
        }
    }

    private <T> RequestProxyWrapper<T> buildRequestProxyWrapper(T data, String method) {
        InternalRequest<T> internalRequest = new InternalRequest<>();
        internalRequest.setData(data);
        RequestProxyWrapper<T> result = new RequestProxyWrapper<>();
        result.setRequest(internalRequest);
        result.setHttpMethod(method);
        return result;
    }

    private BocCustomErrorException getCustomExceptionInstance() {
        BocCustomErrorException bocCustomErrorException = new BocCustomErrorException();
        bocCustomErrorException.setMessage("Sorry something went wrong. Please try after some time");
        bocCustomErrorException.setCode("301");
        return bocCustomErrorException;
    }
}
