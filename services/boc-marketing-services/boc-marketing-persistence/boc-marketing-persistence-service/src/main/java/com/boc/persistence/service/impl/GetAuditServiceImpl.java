package com.boc.persistence.service.impl;

import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.persistence.rest.spec.v1.getaudit.BocCustomErrorException;
import com.backbase.persistence.rest.spec.v1.getaudit.GetAuditPostRequestBody;
import com.backbase.persistence.rest.spec.v1.getaudit.GetAuditPostResponseBody;
import com.boc.persistence.constant.Constants;
import com.boc.persistence.model.CreditUnionAuditInfo;
import com.boc.persistence.repository.CreditUnionAuditInfoRepository;
import com.boc.persistence.service.GetAuditService;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class GetAuditServiceImpl implements GetAuditService {

    private final Logger logger = LoggerFactory.getLogger(GetAuditServiceImpl.class);

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    CreditUnionAuditInfoRepository creditUnionAuditInfoRepository;

    @Override
    public List<GetAuditPostResponseBody> getAudit(GetAuditPostRequestBody getAuditPostRequestBody) {
        int noOfMonths = 3;
        Type listType = new TypeToken<List<GetAuditPostResponseBody>>() {
        }.getType();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startDate = formatter.parse(getAuditPostRequestBody.getStartDate());
            Date endDate = formatter.parse(getAuditPostRequestBody.getEndDate());
            String creditUnionId = getAuditPostRequestBody.getCreditUnionId();

            // Increment a day.
            Date endDatePlusOneDay = new Date(endDate.getTime() + 86400000);
            deleteAuditData(noOfMonths);
            List<CreditUnionAuditInfo> creditUnionAuditInfoList;

            if (getAuditPostRequestBody.getRecordType().equalsIgnoreCase(Constants.AUDIT_RECORD_TYPE_SUCCESS)) {
                creditUnionAuditInfoList = creditUnionAuditInfoRepository.findByCreditUnionIdAndCreateDateBetween(creditUnionId, startDate, endDatePlusOneDay);
            } else {
                creditUnionAuditInfoList = creditUnionAuditInfoRepository.findByCreditUnionIdAndCreateDateBetweenAndOperationResultNot(creditUnionId, startDate, endDatePlusOneDay, Constants.AUDIT_RECORD_TYPE_SUCCESS);
            }
            return modelMapper.map(creditUnionAuditInfoList, listType);
        } catch (ParseException e) {
            logger.info("Error in fetching audit data:" + e.getMessage());
            throw getCustomExceptionInstance();
        }
    }

    @Transactional
    private void deleteAuditData(int noOfMonths) {
        // Delete all data which is older than three months.
        Date currentDateMinusNoOfMonths = new DateTime().minusMonths(noOfMonths).toDate();
        creditUnionAuditInfoRepository.deleteByCreateDateBefore(currentDateMinusNoOfMonths);
    }

    private BocCustomErrorException getCustomExceptionInstance() {
        BocCustomErrorException bocCustomErrorException = new BocCustomErrorException();
        bocCustomErrorException.setMessage("Sorry something went wrong. Please try after some time");
        bocCustomErrorException.setCode("301");
        return bocCustomErrorException;
    }
}
