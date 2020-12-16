package com.boc.persistence.service;

import com.backbase.persistence.rest.spec.v1.creditunion.GetCreditUnionByIdGetResponseBody;
import org.springframework.stereotype.Service;

@Service
public interface CreditUnionService {
    GetCreditUnionByIdGetResponseBody getCreditUnionById(String creditUnionId);
}
