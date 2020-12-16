package com.boc.transformer;

import com.backbase.adminPresentation.rest.spec.v1.admin.BocCustomErrorException;
import com.backbase.persistence.rest.spec.v1.saveaudit.SaveAuditPostRequestBody;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AdminPresentationTransformer {

    public SaveAuditPostRequestBody mapAsSaveAuditPostRequestBody(String requestJson, String creditUnionId, String channel, String operation, String memberId, String operationResult, String txnId) {
        SaveAuditPostRequestBody saveAuditPostRequestBody = new SaveAuditPostRequestBody();
        saveAuditPostRequestBody.setTxnId(txnId);
        saveAuditPostRequestBody.setCreditUnionId(creditUnionId);
        saveAuditPostRequestBody.setMemberId(memberId);
        saveAuditPostRequestBody.setChannel(channel);
        saveAuditPostRequestBody.setOperation(operation);
        saveAuditPostRequestBody.setOperationResult(operationResult);
        saveAuditPostRequestBody.setRequest(requestJson);

        return saveAuditPostRequestBody;
    }

    public String getMemberNumber() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userDetails.getUsername();
        } catch (Exception e) {
            throw getCustomExceptionInstance();
        }
    }

    public String getMemberNumber(String dataFormatter) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String memberId = userDetails.getUsername();
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(dataFormatter);
            return encryptor.encrypt(memberId);
        } catch (Exception e) {
            throw getCustomExceptionInstance();
        }
    }

    public String getEncryptedValue(String data, String dataFormatter) {
        try {
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(dataFormatter);
            return encryptor.encrypt(data);
        } catch (Exception e) {
            throw getCustomExceptionInstance();
        }
    }

    public String getDecryptedValue(String data, String dataFormatter) {
        try {
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(dataFormatter);
            return encryptor.decrypt(data);
        } catch (Exception e) {
            throw getCustomExceptionInstance();
        }
    }

    private BocCustomErrorException getCustomExceptionInstance() {
        BocCustomErrorException bocCustomErrorException = new BocCustomErrorException();
        bocCustomErrorException.setMessage("Sorry something went wrong. Please try after some time");
        bocCustomErrorException.setCode("301");
        return bocCustomErrorException;
    }
}
