package com.boc.persistence.service.impl;

import com.backbase.persistence.rest.spec.v1.getconfigdata.ConfigDataByCreditUnionIdGetResponseBody;
import com.backbase.persistence.rest.spec.v1.getconfigdata.DatabaseInfo;
import com.boc.persistence.model.CreditUnionApiInfo;
import com.boc.persistence.model.CreditUnionAppAuth;
import com.boc.persistence.model.CreditUnionInfo;
import com.boc.persistence.repository.CreditUnionApiInfoRepository;
import com.boc.persistence.repository.CreditUnionAppAuthRepository;
import com.boc.persistence.repository.CreditUnionRepository;
import com.boc.persistence.service.GetConfigDataService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetConfigDataServiceImpl implements GetConfigDataService {

    @Autowired
    CreditUnionRepository creditUnionRepository;
    @Autowired
    CreditUnionAppAuthRepository creditUnionAppAuthRepository;
    @Autowired
    CreditUnionApiInfoRepository creditUnionApiInfoRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public ConfigDataByCreditUnionIdGetResponseBody getConfigData(String creditUnionId) {
        // Get all credit unions
        CreditUnionInfo creditUnionInfo = creditUnionRepository.findByCreditUnionId(creditUnionId);
        // Get all auth app info
        CreditUnionAppAuth creditUnionAppAuth = creditUnionAppAuthRepository.findByCreditUnionId(creditUnionId);
        // Get all api info
        List<CreditUnionApiInfo> creditUnionApiInfoList = creditUnionApiInfoRepository.findByCuId(creditUnionId);

        // Transform to config data
        return mapAsGetConfigDataGetResponseBodyList(creditUnionInfo, creditUnionAppAuth, creditUnionApiInfoList);
    }

    public ConfigDataByCreditUnionIdGetResponseBody mapAsGetConfigDataGetResponseBodyList(CreditUnionInfo creditUnionInfo,
                                                                                    CreditUnionAppAuth creditUnionAppAuth,
                                                                                    List<CreditUnionApiInfo> creditUnionApiInfoList) {
        ConfigDataByCreditUnionIdGetResponseBody getConfigData = new ConfigDataByCreditUnionIdGetResponseBody();
        getConfigData.setCreditUnionId(creditUnionInfo.getCreditUnionId());
        getConfigData.setSource(creditUnionInfo.getSource());
        getConfigData.setCreditUnionApiInfo(mapAsApiInfoList(creditUnionApiInfoList, creditUnionInfo.getCreditUnionId()));
        getConfigData.setCreditUnionAppAuth(mapAsAppAuth(creditUnionAppAuth));
        getConfigData.setDatabaseInfo(mapAsDatabaseInfo(creditUnionAppAuth));
        return getConfigData;
    }

    public DatabaseInfo mapAsDatabaseInfo(CreditUnionAppAuth creditUnionAppAuth) {
        if(creditUnionAppAuth == null)
            return null;
        return modelMapper.map(creditUnionAppAuth, com.backbase.persistence.rest.spec.v1.getconfigdata.DatabaseInfo.class);
    }

    public com.backbase.persistence.rest.spec.v1.getconfigdata.CreditUnionAppAuth mapAsAppAuth(CreditUnionAppAuth creditUnionAppAuth) {
        if(creditUnionAppAuth == null)
            return null;
        return modelMapper.map(creditUnionAppAuth, com.backbase.persistence.rest.spec.v1.getconfigdata.CreditUnionAppAuth.class);
    }

    public List<com.backbase.persistence.rest.spec.v1.getconfigdata.CreditUnionApiInfo> mapAsApiInfoList(List<CreditUnionApiInfo> creditUnionApiInfoList,
                                                                                                         String creditUnionId) {
        if(creditUnionApiInfoList == null || creditUnionId == null)
            return null;
        Type listType = new TypeToken<List<com.backbase.persistence.rest.spec.v1.getconfigdata.CreditUnionApiInfo>>() {
        }.getType();

        List<CreditUnionApiInfo> filteredList = creditUnionApiInfoList.stream().filter(item -> item.getCuId().equals(creditUnionId)).collect(Collectors.toList());
        return modelMapper.map(filteredList, listType);
    }
}
