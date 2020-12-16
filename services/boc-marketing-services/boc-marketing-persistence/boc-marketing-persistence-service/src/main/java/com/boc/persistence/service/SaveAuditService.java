package com.boc.persistence.service;

import com.backbase.persistence.rest.spec.v1.saveaudit.SaveAuditPostRequestBody;
import com.backbase.persistence.rest.spec.v1.saveaudit.SaveAuditPostResponseBody;
import org.springframework.stereotype.Service;

@Service
public interface SaveAuditService {
    SaveAuditPostResponseBody postSaveAudit(SaveAuditPostRequestBody saveauditPostRequestBody);
}
