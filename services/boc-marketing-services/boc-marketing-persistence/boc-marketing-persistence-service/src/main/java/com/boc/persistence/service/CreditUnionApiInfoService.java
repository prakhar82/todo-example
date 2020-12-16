package com.boc.persistence.service;

import com.backbase.persistence.rest.spec.v1.apiinfo.ApiInfoGetResponseBody;
import org.springframework.stereotype.Service;

@Service
public interface CreditUnionApiInfoService {
    ApiInfoGetResponseBody getCreditUnionApiInfo(String creditUnionId, String operation);
}
