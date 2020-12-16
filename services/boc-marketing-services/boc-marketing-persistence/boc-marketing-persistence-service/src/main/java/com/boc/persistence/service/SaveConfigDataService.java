package com.boc.persistence.service;

import com.backbase.persistence.rest.spec.v1.saveconfigdata.SaveConfigDataPostRequestBody;
import com.backbase.persistence.rest.spec.v1.saveconfigdata.SaveConfigDataPostResponseBody;
import org.springframework.stereotype.Service;

@Service
public interface SaveConfigDataService {
    SaveConfigDataPostResponseBody saveConfigData(SaveConfigDataPostRequestBody data);
}
