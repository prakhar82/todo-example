package com.boc.persistence.service;

import com.backbase.persistence.rest.spec.v1.getconfigdata.ConfigDataByCreditUnionIdGetResponseBody;
import org.springframework.stereotype.Service;

@Service
public interface GetConfigDataService {
    ConfigDataByCreditUnionIdGetResponseBody getConfigData(String creditUnionId);
}
