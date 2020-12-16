
package com.boc.persistence.service;

import com.backbase.persistence.rest.spec.v1.authinfo.ApiAuthInfoGetResponseBody;
import org.springframework.stereotype.Service;

@Service
public interface CreditUnionAppAuthService {
    ApiAuthInfoGetResponseBody getAuthInfo(String creditUnionId);
}
