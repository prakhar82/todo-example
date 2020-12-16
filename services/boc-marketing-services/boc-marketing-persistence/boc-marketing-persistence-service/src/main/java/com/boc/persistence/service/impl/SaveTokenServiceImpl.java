package com.boc.persistence.service.impl;

import com.backbase.persistence.rest.spec.v1.savetoken.SaveTokenPostRequestBody;
import com.backbase.persistence.rest.spec.v1.savetoken.SaveTokenPostResponseBody;
import com.boc.persistence.model.CreditUnionAppAuth;
import com.boc.persistence.repository.CreditUnionAppAuthRepository;
import com.boc.persistence.service.SaveTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class SaveTokenServiceImpl implements SaveTokenService {

    @Autowired
    CreditUnionAppAuthRepository creditUnionAppAuthRepository;

    @Value("${tokenExpiryTime}")
    private Integer tokenExpiryTime;

    @Override
    public SaveTokenPostResponseBody saveToken(SaveTokenPostRequestBody savetokenPostRequestBody) {
        CreditUnionAppAuth creditUnionAppAuth = creditUnionAppAuthRepository.findByCreditUnionId(savetokenPostRequestBody.getCreditUnionId());
        creditUnionAppAuth.setTokenExpiryOn(Date.from(new Date().toInstant().plus(tokenExpiryTime, ChronoUnit.HOURS)));
        creditUnionAppAuth.setModifyDate(new Date());
        creditUnionAppAuth.setAccessToken(savetokenPostRequestBody.getAccessToken());
        creditUnionAppAuthRepository.save(creditUnionAppAuth);
        return new SaveTokenPostResponseBody().withMessage("Saved");
    }
}
