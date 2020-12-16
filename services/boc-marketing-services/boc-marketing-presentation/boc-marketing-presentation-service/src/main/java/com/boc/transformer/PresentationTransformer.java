package com.boc.transformer;

import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.integration.rest.spec.v1.marketingIntegration.MarketingWidgetIntegrationPatchRequestBody;
import com.backbase.integration.rest.spec.v1.marketingIntegration.MarketingWidgetIntegrationPostRequestBody;
import com.backbase.integration.rest.spec.v1.marketingIntegration.MarketingWidgetIntegrationPostResponseBody;
import com.backbase.integration.rest.spec.v1.marketingIntegration.RequestBody;
import com.backbase.persistence.rest.spec.v1.apiinfo.ApiInfoGetResponseBody;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.MemberLeadsOpportunitiesPostResponseBody;
import com.backbase.persistence.rest.spec.v1.saveaudit.SaveAuditPostRequestBody;
import com.backbase.presentation.rest.spec.v1.leadsopportunities.*;
import com.boc.constants.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PresentationTransformer {

    public static final Logger logger = LoggerFactory.getLogger(PresentationTransformer.class);
    ObjectMapper objectMapper = new ObjectMapper();

    public MarketingWidgetIntegrationPatchRequestBody mapAsMarketingWidgetIntegrationPatchRequestBody(UpdateMemberResponsePatchRequestBody updateMemberResponsePatchRequestBody, String id, ApiInfoGetResponseBody apiInfoGetResponseBody, String accessToken) {
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatterDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        MarketingWidgetIntegrationPatchRequestBody marketingWidgetIntegrationPatchRequestBody = new MarketingWidgetIntegrationPatchRequestBody();
        RequestBody requestBody = new RequestBody();

        String baseUrl = apiInfoGetResponseBody.getBaseUrl();
        marketingWidgetIntegrationPatchRequestBody.setId(id);
        marketingWidgetIntegrationPatchRequestBody.setAccessToken(accessToken);
        marketingWidgetIntegrationPatchRequestBody.setBaseUrl(baseUrl);
        requestBody.setBocforgeMemberResponseC(updateMemberResponsePatchRequestBody.getBocforgeMemberResponse());
        requestBody.setBocforgeMemberResponseDateC(formatterDate.format(new Date()));
        requestBody.setBocforgeMemberResponseTimeC(formatterDateTime.format(new Date()));
        requestBody.setBocforgeButtonCodeC(updateMemberResponsePatchRequestBody.getBocforgeButtonCode());
        requestBody.setBocforgeChoiceCodeC(updateMemberResponsePatchRequestBody.getBocforgeChoiceCode());
        requestBody.setBocforgeMemberChoiceTextC(updateMemberResponsePatchRequestBody.getBocforgeMemberChoiceText());
        marketingWidgetIntegrationPatchRequestBody.setRequestBody(requestBody);

        return marketingWidgetIntegrationPatchRequestBody;
    }

    public MarketingWidgetIntegrationPostRequestBody mapAsMarketingWidgetIntegrationPostRequestBody(String accessToken, ApiInfoGetResponseBody apiInfoGetResponseBody) {
        MarketingWidgetIntegrationPostRequestBody marketingWidgetIntegrationPostRequestBody = new MarketingWidgetIntegrationPostRequestBody();
        String baseUrl = apiInfoGetResponseBody.getBaseUrl();
        marketingWidgetIntegrationPostRequestBody.setAccessToken(accessToken);
        marketingWidgetIntegrationPostRequestBody.setBaseUrl(baseUrl);
        marketingWidgetIntegrationPostRequestBody.setMemberNumber(getMemberNumber());

        return marketingWidgetIntegrationPostRequestBody;
    }

    public FetchLeadsAndOpportunitiesPostResponseBody mapAsFetchLeadsAndOpportunitiesPostResponseBodyDb(List<Record> recordsDb) {
        FetchLeadsAndOpportunitiesPostResponseBody fetchLeadsAndOpportunitiesPostResponseBody = new FetchLeadsAndOpportunitiesPostResponseBody();
        if (recordsDb == null) {
            fetchLeadsAndOpportunitiesPostResponseBody.setTotalSize(0);
            fetchLeadsAndOpportunitiesPostResponseBody.setRecords(new ArrayList<>());
            return fetchLeadsAndOpportunitiesPostResponseBody;
        }
        fetchLeadsAndOpportunitiesPostResponseBody.setTotalSize(recordsDb.size());
        fetchLeadsAndOpportunitiesPostResponseBody.setRecords(recordsDb);
        return fetchLeadsAndOpportunitiesPostResponseBody;
    }

    public SaveAuditPostRequestBody mapPresentationAsSaveAuditPostRequestBody(String requestJson, String creditUnionId, String operation, String operationResult,
                                                                              String txnId, String leadsOpportunityId,
                                                                              RequestBody requestBody, String dataFormatter) {
        SaveAuditPostRequestBody saveAuditPostRequestBody = new SaveAuditPostRequestBody();
        saveAuditPostRequestBody.setTxnId(txnId);
        saveAuditPostRequestBody.setCreditUnionId(creditUnionId);
        saveAuditPostRequestBody.setMemberId(getMemberNumber(dataFormatter));
        saveAuditPostRequestBody.setChannel(Constants.CHANNEL_USER);
        saveAuditPostRequestBody.setOperation(operation);
        saveAuditPostRequestBody.setOperationResult(operationResult);
        saveAuditPostRequestBody.setRequest(requestJson);
        saveAuditPostRequestBody.setNotificationId(leadsOpportunityId);
        if(requestBody !=null){
            saveAuditPostRequestBody.setButtonCode(requestBody.getBocforgeButtonCodeC());
            saveAuditPostRequestBody.setButtonText(requestBody.getBocforgeMemberResponseC());
            saveAuditPostRequestBody.setChoiceCode(requestBody.getBocforgeChoiceCodeC());
            saveAuditPostRequestBody.setChoiceText(requestBody.getBocforgeMemberChoiceTextC());
            saveAuditPostRequestBody.setResponseDate(requestBody.getBocforgeMemberResponseDateC());
            saveAuditPostRequestBody.setResponseTime(requestBody.getBocforgeMemberResponseTimeC());
        }

        return saveAuditPostRequestBody;
    }

    public List<Record> mapDatabaseLeadsOpportunitiesAsRecords(List<MemberLeadsOpportunitiesPostResponseBody> records) {
        List<Record> lstRecords = new ArrayList<>();
        if (records == null || records.isEmpty()) {
            return lstRecords;
        }
        for (MemberLeadsOpportunitiesPostResponseBody record : records) {
            if (isRecordValid(record.getBocforgeLoHeaderTextC(), Integer.parseInt(record.getBocforgeButtonCodeC()))) {
                Record newRecord = new Record();
                com.backbase.integration.rest.spec.v1.marketingIntegration.Record rec = objectMapper.convertValue(record, com.backbase.integration.rest.spec.v1.marketingIntegration.Record.class);

                List<Promotion> promotions = mapAsPromotions(rec);
                newRecord.setId(record.getId());
                newRecord.setBocforgeFinancialAccountType(record.getBocforgeFinancialAccountTypeC());
                newRecord.setBocforgeLoHeaderText(record.getBocforgeLoHeaderTextC());
                newRecord.setBocforgeMemberNo(record.getBocforgeMemberNoC());
                newRecord.setBocforgeMemberResponse(record.getBocforgeMemberResponseC());
                newRecord.setBocforgeCRMPriority(String.valueOf(record.getBocforgeCRMPriorityC()));
                if (record.getBocforgeButtonCodeC() != null)
                    newRecord.setBocforgeButtonCode(Integer.parseInt(record.getBocforgeButtonCodeC()));
                if (record.getBocforgeChoiceCodeC() != null)
                    newRecord.setBocforgeChoiceCode(Integer.parseInt(record.getBocforgeChoiceCodeC()));

                newRecord.setPromotions(promotions);
                lstRecords.add(newRecord);
            }
        }
        return lstRecords;
    }

    public boolean isNullOrEmpty(String data) {
        return !(data != null && !data.trim().equals(""));
    }

    public FetchLeadsAndOpportunitiesPostResponseBody mapAsFetchLeadsAndOpportunitiesPostResponseBody(MarketingWidgetIntegrationPostResponseBody marketingWidgetIntegrationPostResponseBody) {
        FetchLeadsAndOpportunitiesPostResponseBody fetchLeadsAndOpportunitiesPostResponseBody = new FetchLeadsAndOpportunitiesPostResponseBody();
        if (marketingWidgetIntegrationPostResponseBody == null){
            throw getCustomExceptionInstance();
        }
        List<Record> records = mapAsRecords(marketingWidgetIntegrationPostResponseBody.getRecords());
        fetchLeadsAndOpportunitiesPostResponseBody.setTotalSize(records.size());
        fetchLeadsAndOpportunitiesPostResponseBody.setRecords(records);
        return fetchLeadsAndOpportunitiesPostResponseBody;
    }

    public List<Record> mapAsRecords(List<com.backbase.integration.rest.spec.v1.marketingIntegration.Record> records) {
        if (records == null)
            return Collections.emptyList();
        List<Record> lstRecords = new ArrayList<>();
        for (com.backbase.integration.rest.spec.v1.marketingIntegration.Record record : records) {
            if (isRecordValid(record.getBocforgeLoHeaderTextC(), record.getBocforgeButtonCodeC())) {
                Record newRecord = new Record();
                List<Promotion> promotions = mapAsPromotions(record);
                newRecord.setId(record.getId());
                newRecord.setBocforgeFinancialAccountType(record.getBocforgeFinancialAccountTypeC());
                newRecord.setBocforgeLoHeaderText(record.getBocforgeLoHeaderTextC());
                newRecord.setBocforgeMemberNo(record.getBocforgeMemberNoC());
                newRecord.setBocforgeMemberResponse(record.getBocforgeMemberResponseC());
                newRecord.setBocforgeButtonCode(record.getBocforgeButtonCodeC());
                newRecord.setBocforgeChoiceCode(record.getBocforgeChoiceCodeC());
                newRecord.setBocforgeCRMPriority(record.getBocforgeCRMPriorityC());
                newRecord.setPromotions(promotions);
                lstRecords.add(newRecord);
            }
        }

        return lstRecords;
    }

    public boolean isRecordValid(String headerText, Integer buttonCode) {
        List<Integer> negativeResponseButtonCodes = Arrays.asList(5, 6, null);
        return !isNullOrEmpty(headerText) && negativeResponseButtonCodes.contains(buttonCode);
    }

    public List<Promotion> mapAsPromotions(com.backbase.integration.rest.spec.v1.marketingIntegration.Record record) {
        if (record == null)
            return Collections.emptyList();
        String promotionStartDate1 = convertToYYYYMMDDIfDateInMillis(record.getBocforgePromotion1StartDateC());
        String promotionEndDate1 = convertToYYYYMMDDIfDateInMillis(record.getBocforgePromotion1EndDateC());
        String promotionStartDate2 = convertToYYYYMMDDIfDateInMillis(record.getBocforgePromotion2StartDateC());
        String promotionEndDate2 = convertToYYYYMMDDIfDateInMillis(record.getBocforgePromotion2EndDateC());
        String promotionStartDate3 = convertToYYYYMMDDIfDateInMillis(record.getBocforgePromotion3StartDateC());
        String promotionEndDate3 = convertToYYYYMMDDIfDateInMillis(record.getBocforgePromotion3EndDateC());
        String promotionStartDate4 = convertToYYYYMMDDIfDateInMillis(record.getBocforgePromotion4StartDateC());
        String promotionEndDate4 = convertToYYYYMMDDIfDateInMillis(record.getBocforgePromotion4EndDateC());
        String promotionStartDate5 = convertToYYYYMMDDIfDateInMillis(record.getBocforgePromotion5StartDateC());
        String promotionEndDate5 = convertToYYYYMMDDIfDateInMillis(record.getBocforgePromotion5EndDateC());
        String promotionStartDate6 = convertToYYYYMMDDIfDateInMillis(record.getBocforgePromotion6StartDateC());
        String promotionEndDate6 = convertToYYYYMMDDIfDateInMillis(record.getBocforgePromotion6EndDateC());
        List<Promotion> promotions = new ArrayList<>();
        if (!isNullOrEmpty(record.getBocforgePromotionDescription1C()) && isPromotionChoiceValid(promotionStartDate1, promotionEndDate1)) {
            Promotion promotion = new Promotion();
            promotion.setBocforgePromotionId(1);
            promotion.setBocforgePromotionDescription(record.getBocforgePromotionDescription1C());
            promotion.setBocforgePromotionStartDate(promotionStartDate1);
            promotion.setBocforgePromotionEndDate(promotionEndDate1);
            promotions.add(promotion);
        }
        if (!isNullOrEmpty(record.getBocforgePromotionDescription2C()) && isPromotionChoiceValid(promotionStartDate2, promotionEndDate2)) {
            Promotion promotion = new Promotion();
            promotion.setBocforgePromotionId(2);
            promotion.setBocforgePromotionDescription(record.getBocforgePromotionDescription2C());
            promotion.setBocforgePromotionStartDate(promotionStartDate2);
            promotion.setBocforgePromotionEndDate(promotionEndDate2);
            promotions.add(promotion);
        }
        if (!isNullOrEmpty(record.getBocforgePromotionDescription3C()) && isPromotionChoiceValid(promotionStartDate3, promotionEndDate3)) {
            Promotion promotion = new Promotion();
            promotion.setBocforgePromotionId(3);
            promotion.setBocforgePromotionDescription(record.getBocforgePromotionDescription3C());
            promotion.setBocforgePromotionStartDate(promotionStartDate3);
            promotion.setBocforgePromotionEndDate(promotionEndDate3);
            promotions.add(promotion);
        }
        if (!isNullOrEmpty(record.getBocforgePromotionDescription4C()) && isPromotionChoiceValid(promotionStartDate4, promotionEndDate4)) {
            Promotion promotion = new Promotion();
            promotion.setBocforgePromotionId(4);
            promotion.setBocforgePromotionDescription(record.getBocforgePromotionDescription4C());
            promotion.setBocforgePromotionStartDate(promotionStartDate4);
            promotion.setBocforgePromotionEndDate(promotionEndDate4);
            promotions.add(promotion);
        }
        if (!isNullOrEmpty(record.getBocforgePromotionDescription5C()) && isPromotionChoiceValid(promotionStartDate5, promotionEndDate5)) {
            Promotion promotion = new Promotion();
            promotion.setBocforgePromotionId(5);
            promotion.setBocforgePromotionDescription(record.getBocforgePromotionDescription5C());
            promotion.setBocforgePromotionStartDate(promotionStartDate5);
            promotion.setBocforgePromotionEndDate(promotionEndDate5);
            promotions.add(promotion);
        }
        if (!isNullOrEmpty(record.getBocforgePromotionDescription6C()) && isPromotionChoiceValid(promotionStartDate6, promotionEndDate6)) {
            Promotion promotion = new Promotion();
            promotion.setBocforgePromotionId(6);
            promotion.setBocforgePromotionDescription(record.getBocforgePromotionDescription6C());
            promotion.setBocforgePromotionStartDate(promotionStartDate6);
            promotion.setBocforgePromotionEndDate(promotionEndDate6);
            promotions.add(promotion);
        }
        return promotions;
    }

    private String convertToYYYYMMDDIfDateInMillis(String data) {
        if(data == null)
            return null;
        if(!data.contains("-")){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            long test = Long.parseLong(data);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(test);
            return formatter.format(calendar.getTime());
        }
        return data;
    }

    public boolean isPromotionChoiceValid(String bocforgePromotionStartDate, String bocforgePromotionEndDate) {
        if (isNullOrEmpty(bocforgePromotionStartDate) || isNullOrEmpty(bocforgePromotionEndDate))
            return false;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date promotionStartDate = formatter.parse(bocforgePromotionStartDate);
            Date promotionEndDate = formatter.parse(bocforgePromotionEndDate);
            Date promotionEndDatePlusOneDay = new Date(promotionEndDate.getTime() + 86400000);
            Date currentDate = new Date();
            return currentDate.after(promotionStartDate) && currentDate.before(promotionEndDatePlusOneDay);
        } catch (ParseException e) {
            logger.info("Error in promotion choice comparison:" + e.getMessage());
            return false;
        }
    }

    public String getMemberNumber() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userDetails.getUsername();
        } catch (Exception e) {
            logger.info("Error in retrieving member Number");
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
            logger.info("Error in retrieving member Number");
            throw getCustomExceptionInstance();
        }
    }

    public BocCustomErrorException getCustomExceptionInstance() {
        BocCustomErrorException bocCustomErrorException = new BocCustomErrorException();
        bocCustomErrorException.setMessage("Sorry something went wrong. Please try after some time");
        bocCustomErrorException.setCode("301");
        return bocCustomErrorException;
    }
}
