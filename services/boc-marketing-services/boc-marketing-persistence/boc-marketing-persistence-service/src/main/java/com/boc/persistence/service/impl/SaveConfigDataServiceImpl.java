package com.boc.persistence.service.impl;

import com.backbase.persistence.rest.spec.v1.saveconfigdata.SaveConfigDataPostRequestBody;
import com.backbase.persistence.rest.spec.v1.saveconfigdata.SaveConfigDataPostResponseBody;
import com.boc.persistence.constant.Constants;
import com.boc.persistence.model.CreditUnionApiInfo;
import com.boc.persistence.model.CreditUnionAppAuth;
import com.boc.persistence.repository.CreditUnionApiInfoRepository;
import com.boc.persistence.repository.CreditUnionAppAuthRepository;
import com.boc.persistence.service.SaveConfigDataService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
public class SaveConfigDataServiceImpl implements SaveConfigDataService {
    @Autowired
    CreditUnionApiInfoRepository creditUnionApiInfoRepository;

    @Autowired
    CreditUnionAppAuthRepository creditUnionAppAuthRepository;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public SaveConfigDataPostResponseBody saveConfigData(SaveConfigDataPostRequestBody saveConfigDataPostRequestBody) {
        // save api info
        saveApiInfo(saveConfigDataPostRequestBody);
        // save app auth
        saveAppAuth(saveConfigDataPostRequestBody);
        return new SaveConfigDataPostResponseBody().withMessage(Constants.MESSAGE_SUCCESS);
    }

    public void saveAppAuth(SaveConfigDataPostRequestBody saveConfigDataPostRequestBody) {
        if (saveConfigDataPostRequestBody.getCreditUnionAppAuth() != null){
            CreditUnionAppAuth creditUnionAppAuth = mapAsAppAuth(saveConfigDataPostRequestBody);
            CreditUnionAppAuth creditUnionAppAuthResult = creditUnionAppAuthRepository.findByCreditUnionId(saveConfigDataPostRequestBody.getCreditUnionId());
            if (creditUnionAppAuthResult != null) {
                creditUnionAppAuth.setId(creditUnionAppAuthResult.getId());
                creditUnionAppAuth.setCreateDate(creditUnionAppAuthResult.getCreateDate());
            }
            creditUnionAppAuthRepository.save(creditUnionAppAuth);
        }
        if(saveConfigDataPostRequestBody.getDatabaseInfo() != null){
            CreditUnionAppAuth creditUnionAppAuth = mapAsAppAuth(saveConfigDataPostRequestBody);
            CreditUnionAppAuth creditUnionAppAuthResult = creditUnionAppAuthRepository.findByCreditUnionId(saveConfigDataPostRequestBody.getCreditUnionId());
            if (creditUnionAppAuthResult != null) {
                creditUnionAppAuth.setId(creditUnionAppAuthResult.getId());
                creditUnionAppAuth.setCreateDate(creditUnionAppAuthResult.getCreateDate());
            }
            creditUnionAppAuthRepository.save(creditUnionAppAuth);
        }

    }

    public void saveApiInfo(SaveConfigDataPostRequestBody saveConfigDataPostRequestBody) {
        if (saveConfigDataPostRequestBody.getCreditUnionApiInfo() != null) {
            List<CreditUnionApiInfo> creditUnionApiInfoList = mapAsApiInfo(saveConfigDataPostRequestBody);
            creditUnionApiInfoList.forEach(item -> {
                CreditUnionApiInfo creditUnionApiInfoResult = creditUnionApiInfoRepository.findByCuIdAndOperation(item.getCuId(), item.getOperation());
                if (creditUnionApiInfoResult != null) {
                    item.setId(creditUnionApiInfoResult.getId());
                    item.setCreateDate(creditUnionApiInfoResult.getCreateDate());
                }
                creditUnionApiInfoRepository.save(item);
            });
        }
    }

    private CreditUnionAppAuth mapAsAppAuth(SaveConfigDataPostRequestBody saveConfigDataPostRequestBody) {
        CreditUnionAppAuth creditUnionAppAuth = new CreditUnionAppAuth();
        creditUnionAppAuth.setCreditUnionId(saveConfigDataPostRequestBody.getCreditUnionId());
        if (saveConfigDataPostRequestBody.getCreditUnionAppAuth() != null) {
            creditUnionAppAuth.setHeader(saveConfigDataPostRequestBody.getCreditUnionAppAuth().getHeader());
            creditUnionAppAuth.setKeystorePass(saveConfigDataPostRequestBody.getCreditUnionAppAuth().getKeystorePass());
            creditUnionAppAuth.setKeyAlias(saveConfigDataPostRequestBody.getCreditUnionAppAuth().getKeyAlias());
            creditUnionAppAuth.setKeyPass(saveConfigDataPostRequestBody.getCreditUnionAppAuth().getKeyPass());
            creditUnionAppAuth.setClientId(saveConfigDataPostRequestBody.getCreditUnionAppAuth().getClientId());
            creditUnionAppAuth.setEmail(saveConfigDataPostRequestBody.getCreditUnionAppAuth().getEmail());
            creditUnionAppAuth.setDomain(saveConfigDataPostRequestBody.getCreditUnionAppAuth().getDomain());
        }
        if (saveConfigDataPostRequestBody.getDatabaseInfo() != null) {
            creditUnionAppAuth.setUrl(saveConfigDataPostRequestBody.getDatabaseInfo().getUrl());
            creditUnionAppAuth.setUsername(saveConfigDataPostRequestBody.getDatabaseInfo().getUsername());
            creditUnionAppAuth.setPassword(saveConfigDataPostRequestBody.getDatabaseInfo().getPassword());
            creditUnionAppAuth.setDatabaseType(saveConfigDataPostRequestBody.getDatabaseInfo().getDatabaseType());
        }
        creditUnionAppAuth.setCreateDate(new Date());
        creditUnionAppAuth.setModifyDate(new Date());
        return creditUnionAppAuth;
    }

    public List<CreditUnionApiInfo> mapAsApiInfo(SaveConfigDataPostRequestBody saveConfigDataPostRequestBody) {
        Type listType = new TypeToken<List<CreditUnionApiInfo>>() {
        }.getType();
        List<CreditUnionApiInfo> creditUnionApiInfo = modelMapper.map(saveConfigDataPostRequestBody.getCreditUnionApiInfo(), listType);
        creditUnionApiInfo.forEach(item -> {
            item.setCuId(saveConfigDataPostRequestBody.getCreditUnionId());
            item.setModifyDate(new Date());
            item.setCreateDate(new Date());
        });
        return creditUnionApiInfo;
    }
}
