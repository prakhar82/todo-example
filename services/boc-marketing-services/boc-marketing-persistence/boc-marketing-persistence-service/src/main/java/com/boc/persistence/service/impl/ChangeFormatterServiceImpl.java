package com.boc.persistence.service.impl;

import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.persistence.rest.spec.v1.changeFormatter.BocCustomErrorException;
import com.backbase.persistence.rest.spec.v1.changeFormatter.ChangeFormatterPostRequestBody;
import com.backbase.persistence.rest.spec.v1.changeFormatter.ChangeFormatterPostResponseBody;
import com.boc.persistence.constant.Constants;
import com.boc.persistence.model.CreditUnionAuditInfo;
import com.boc.persistence.model.CreditUnionLeadOpportunity;
import com.boc.persistence.repository.CULeadOpportunityRepository;
import com.boc.persistence.repository.CreditUnionAuditInfoRepository;
import com.boc.persistence.service.ChangeFormatterService;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeFormatterServiceImpl implements ChangeFormatterService {

    private final Logger logger = LoggerFactory.getLogger(ChangeFormatterServiceImpl.class);

    @Autowired
    CULeadOpportunityRepository cuLeadOpportunityRepository;

    @Autowired
    CreditUnionAuditInfoRepository creditUnionAuditInfoRepository;

    @Override
    public ChangeFormatterPostResponseBody changeFormatter(ChangeFormatterPostRequestBody changeFormatterPostRequestBody) {

        ChangeFormatterPostResponseBody changeFormatterPostResponseBody = new ChangeFormatterPostResponseBody();
        try {
            StandardPBEStringEncryptor decrypter = new StandardPBEStringEncryptor();
            decrypter.setPassword(changeFormatterPostRequestBody.getCurrentDataFormatter());

            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(changeFormatterPostRequestBody.getNewDataFormatter());

            encryptCreditUnionAuditLeadOpportunity(decrypter, encryptor, changeFormatterPostRequestBody.getCreditUnionId());
            encryptCreditUnionAuditInfo(decrypter, encryptor, changeFormatterPostRequestBody.getCreditUnionId());
        } catch (Exception e) {
            logger.info("***** Exception inside ChangeFormatterServiceImpl.changeFormatter() *** : " +e.getMessage());
            throw new BocCustomErrorException();
        }

        changeFormatterPostResponseBody.setMessage(Constants.MESSAGE_SAVED);
        return changeFormatterPostResponseBody;
    }

    private void encryptCreditUnionAuditLeadOpportunity(StandardPBEStringEncryptor decrypter, StandardPBEStringEncryptor encryptor, String creditUnionId) {

        List<CreditUnionLeadOpportunity> creditUnionLeadOpportunities = cuLeadOpportunityRepository.findByCreditUnionId(creditUnionId);

        if (creditUnionLeadOpportunities != null && !creditUnionLeadOpportunities.isEmpty()) {

            for (CreditUnionLeadOpportunity creditUnionLeadOpportunity : creditUnionLeadOpportunities) {
                if (creditUnionLeadOpportunity != null && creditUnionLeadOpportunity.getBocforgeMemberNumber() != null && !creditUnionLeadOpportunity.getBocforgeMemberNumber().equals("")) {

                    String memberNo = decrypter.decrypt(creditUnionLeadOpportunity.getBocforgeMemberNumber());
                    creditUnionLeadOpportunity.setBocforgeMemberNumber(encryptor.encrypt(memberNo));
                }
            }
        }
        cuLeadOpportunityRepository.save(creditUnionLeadOpportunities);
    }

    private void encryptCreditUnionAuditInfo(StandardPBEStringEncryptor decrypter, StandardPBEStringEncryptor encryptor, String creditUnionId) {

        List<CreditUnionAuditInfo> creditUnionAuditInfoList = creditUnionAuditInfoRepository.findByCreditUnionId(creditUnionId);
        if (creditUnionAuditInfoList != null && !creditUnionAuditInfoList.isEmpty()) {

            for (CreditUnionAuditInfo creditUnionAuditInfo : creditUnionAuditInfoList) {
                if (creditUnionAuditInfo != null && creditUnionAuditInfo.getMemberId() != null && !creditUnionAuditInfo.getMemberId().equals("")) {

                    String memberNo = decrypter.decrypt(creditUnionAuditInfo.getMemberId());
                    creditUnionAuditInfo.setMemberId(encryptor.encrypt(memberNo));
                }
            }
        }
        creditUnionAuditInfoRepository.save(creditUnionAuditInfoList);
    }
}
