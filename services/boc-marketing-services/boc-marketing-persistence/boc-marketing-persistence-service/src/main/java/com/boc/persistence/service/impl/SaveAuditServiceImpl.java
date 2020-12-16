package com.boc.persistence.service.impl;

import com.backbase.persistence.rest.spec.v1.saveaudit.SaveAuditPostRequestBody;
import com.backbase.persistence.rest.spec.v1.saveaudit.SaveAuditPostResponseBody;
import com.boc.persistence.constant.Constants;
import com.boc.persistence.model.CreditUnionAuditInfo;
import com.boc.persistence.repository.CreditUnionAuditInfoRepository;
import com.boc.persistence.service.SaveAuditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SaveAuditServiceImpl implements SaveAuditService {


    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    CreditUnionAuditInfoRepository creditUnionAuditInfoRepository;

    @Override
    public SaveAuditPostResponseBody postSaveAudit(SaveAuditPostRequestBody saveAuditPostRequestBody) {
        CreditUnionAuditInfo creditUnionAuditInfo;
        creditUnionAuditInfo = objectMapper.convertValue(saveAuditPostRequestBody, CreditUnionAuditInfo.class);
        creditUnionAuditInfo.setCreateDate(new Date());
        creditUnionAuditInfoRepository.save(creditUnionAuditInfo);
        SaveAuditPostResponseBody saveAuditPostResponseBody = new SaveAuditPostResponseBody();
        saveAuditPostResponseBody.setMessage(Constants.MESSAGE_SUCCESS);
        return saveAuditPostResponseBody;
    }
}
