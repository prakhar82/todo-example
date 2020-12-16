package com.boc.persistence.service.impl;

import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.persistence.rest.spec.v1.creditunion.GetCreditUnionByIdGetResponseBody;
import com.backbase.persistence.rest.spec.v1.getconfigdata.BocCustomErrorException;
import com.boc.persistence.model.CreditUnionInfo;
import com.boc.persistence.repository.CreditUnionRepository;
import com.boc.persistence.service.CreditUnionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditUnionServiceImpl implements CreditUnionService {

    private final Logger logger = LoggerFactory.getLogger(CreditUnionServiceImpl.class);
    @Autowired
    CreditUnionRepository creditUnionRepository;

    @Override
    public GetCreditUnionByIdGetResponseBody getCreditUnionById(String creditUnionId) {

        logger.info("Inside CreditUnionServiceImpl to get CU ID");
        ModelMapper modelMapper = new ModelMapper();
        CreditUnionInfo creditUnionInfo = creditUnionRepository.findOne(creditUnionId);
        if(creditUnionInfo == null){
            throw getCustomExceptionInstance();
        }
        return modelMapper.map(creditUnionInfo, GetCreditUnionByIdGetResponseBody.class);
    }

    private BocCustomErrorException getCustomExceptionInstance() {
        BocCustomErrorException bocCustomErrorException = new BocCustomErrorException();
        bocCustomErrorException.setMessage("Sorry something went wrong. Please try after some time");
        bocCustomErrorException.setCode("301");
        return bocCustomErrorException;
    }
}
