package com.boc.auth.transformer;

import com.backbase.authentication.rest.spec.v1.authentication.AuthenticationPostResponseBody;
import com.boc.api.model.InlineResponse200;

public class AuthResponseTransformer {

    private AuthResponseTransformer(){}

    public static AuthenticationPostResponseBody transformAuthResponse (InlineResponse200 inlineResponse200) {

        AuthenticationPostResponseBody authenticationPostResponseBody = new AuthenticationPostResponseBody();
        authenticationPostResponseBody.setAccessToken(inlineResponse200.getAccessToken());
        authenticationPostResponseBody.setInstanceUrl(inlineResponse200.getInstanceUrl());
        authenticationPostResponseBody.setId(inlineResponse200.getId());
        authenticationPostResponseBody.setTokenType(inlineResponse200.getTokenType());
        authenticationPostResponseBody.setIssuedAt(inlineResponse200.getIssuedAt());
        authenticationPostResponseBody.setSignature(inlineResponse200.getSignature());

        return authenticationPostResponseBody;
    }
}
