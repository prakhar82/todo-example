package com.boc.auth.listener;

import com.backbase.authentication.listener.spec.v1.authentication.AuthenticationListener;
import com.backbase.authentication.rest.spec.v1.authentication.AuthenticationPostRequestBody;
import com.backbase.authentication.rest.spec.v1.authentication.AuthenticationPostResponseBody;
import com.backbase.buildingblocks.backend.communication.event.annotations.RequestListener;
import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.boc.api.model.InlineResponse200;
import com.boc.api.spec.AccessTokenApi;
import com.boc.auth.constant.Constants;
import com.boc.auth.token.TokenManager;
import com.boc.auth.transformer.AuthResponseTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequestListener
public class AuthenticationListenerImpl implements AuthenticationListener {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationListenerImpl.class);

    @Autowired
    private final AccessTokenApi accessTokenApi;

    @Autowired
    TokenManager tokenManager;

    @Autowired
    public AuthenticationListenerImpl(AccessTokenApi accessTokenApi) {
        this.accessTokenApi = accessTokenApi;
    }

    @Override
    public RequestProxyWrapper<AuthenticationPostResponseBody> postAuthentication(RequestProxyWrapper<AuthenticationPostRequestBody> authenticationPostRequestBody,
                                                                                  Exchange exchange) {
        logger.info("DEMO_001: Inside authentication listener to get new access token from salesforce");
        try {
            AuthenticationPostResponseBody authenticationPostResponseBody = parseAuthResponse(authenticationPostRequestBody.getRequest().getData());
            return buildRequestProxyWrapper(authenticationPostResponseBody, "POST");
        } catch (Exception exception) {
            logger.info("Exception while getting Authentication " + exception.getMessage());
        }
        return null;
    }

    public AuthenticationPostResponseBody parseAuthResponse(AuthenticationPostRequestBody authenticationPostRequestBody) throws IOException {
        String authJson = getOauthAccessToken(authenticationPostRequestBody);
        InlineResponse200 inlineResponse200 = new ObjectMapper().readValue(authJson, InlineResponse200.class);
        return AuthResponseTransformer.transformAuthResponse(inlineResponse200);
    }

    public String getOauthAccessToken(AuthenticationPostRequestBody authenticationPostRequestBody) throws IOException {

        String responseBody = "";
        String signedJwtToken = "";
        try {
            if (authenticationPostRequestBody != null && authenticationPostRequestBody.getCreditUnionId() != null) {
                signedJwtToken = tokenManager.getSignedJWTToken(authenticationPostRequestBody);
            }

            responseBody = executeRequest(signedJwtToken);
        } catch (Exception exception) {
            logger.info("Exception while executing request " + exception.getMessage());
        }
        return responseBody;
    }

    public String executeRequest(String signedToken) {

        String url = accessTokenApi.getApiClient().getBasePath();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(Constants.GRANT_TYPE_KEY, Constants.GRANT_TYPE_VALUE);
        map.add(Constants.ASSERTION, signedToken);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();
    }

    public  <T> RequestProxyWrapper<T> buildRequestProxyWrapper(T data, String method) {
        InternalRequest<T> internalRequest = new InternalRequest<>();
        internalRequest.setData(data);
        RequestProxyWrapper<T> result = new RequestProxyWrapper<>();
        result.setRequest(internalRequest);
        result.setHttpMethod(method);
        return result;
    }
}
