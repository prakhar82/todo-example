package com.boc.persistence.service.impl;

import com.backbase.persistence.rest.spec.v1.authinfo.ApiAuthInfoGetResponseBody;
import com.boc.persistence.model.CreditUnionAppAuth;
import com.boc.persistence.repository.CreditUnionAppAuthRepository;
import com.boc.persistence.service.CreditUnionAppAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditUnionAppAuthServiceImpl implements CreditUnionAppAuthService {

    @Autowired
    CreditUnionAppAuthRepository creditUnionAppAuthRepository;

    @Override
    public ApiAuthInfoGetResponseBody getAuthInfo(String creditUnionId){
        CreditUnionAppAuth creditUnionAppAuth = creditUnionAppAuthRepository.findByCreditUnionId(creditUnionId);
        if(creditUnionAppAuth == null)
            return new ApiAuthInfoGetResponseBody();
        return new ObjectMapper().convertValue(creditUnionAppAuth, ApiAuthInfoGetResponseBody.class);
    }
}
