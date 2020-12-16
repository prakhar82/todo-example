package com.boc.persistence.service;

import com.backbase.persistence.rest.spec.v1.savetoken.SaveTokenPostRequestBody;
import com.backbase.persistence.rest.spec.v1.savetoken.SaveTokenPostResponseBody;

public interface SaveTokenService {
    SaveTokenPostResponseBody saveToken(SaveTokenPostRequestBody savetokenPostRequestBody);
}
