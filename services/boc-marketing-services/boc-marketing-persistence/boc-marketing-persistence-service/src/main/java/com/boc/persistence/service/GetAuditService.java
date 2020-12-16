package com.boc.persistence.service;

import com.backbase.persistence.rest.spec.v1.getaudit.GetAuditPostRequestBody;
import com.backbase.persistence.rest.spec.v1.getaudit.GetAuditPostResponseBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetAuditService {
    List<GetAuditPostResponseBody> getAudit(GetAuditPostRequestBody getAuditPostRequestBody);
}
