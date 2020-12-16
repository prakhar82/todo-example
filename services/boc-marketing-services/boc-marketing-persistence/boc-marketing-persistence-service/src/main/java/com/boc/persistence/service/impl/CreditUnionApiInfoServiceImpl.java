package com.boc.persistence.service.impl;

import com.backbase.persistence.rest.spec.v1.apiinfo.ApiInfoGetResponseBody;
import com.boc.persistence.model.CreditUnionApiInfo;
import com.boc.persistence.repository.CreditUnionApiInfoRepository;
import com.boc.persistence.service.CreditUnionApiInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditUnionApiInfoServiceImpl implements CreditUnionApiInfoService {

    @Autowired
    CreditUnionApiInfoRepository creditUnionApiInfoRepository;

    @Override
    public ApiInfoGetResponseBody getCreditUnionApiInfo(String creditUnionId, String operation) {
        ObjectMapper objectMapper = new ObjectMapper();

        CreditUnionApiInfo creditUnionApiInfo = creditUnionApiInfoRepository.findByCuIdAndOperation(creditUnionId, operation);
        if (creditUnionApiInfo == null)
            return new ApiInfoGetResponseBody();

        return objectMapper.convertValue(creditUnionApiInfo, ApiInfoGetResponseBody.class);
    }
}
