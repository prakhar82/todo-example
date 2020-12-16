package com.boc.persistence.service.impl;

import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.persistence.rest.spec.v1.leadsopportunities.*;
import com.boc.persistence.constant.Constants;
import com.boc.persistence.model.CreditUnionLeadOpportunity;
import com.boc.persistence.repository.CULeadOpportunityRepository;
import com.boc.persistence.service.CULeadOpportunityService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CULeadOpportunityServiceImpl implements CULeadOpportunityService {

    @Autowired
    CULeadOpportunityRepository cuLeadOpportunityRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public List<LeadsAndOpportunitiesGetResponseBody> getLeadsOpportunities(String creditUnionId) {
        List<CreditUnionLeadOpportunity> creditUnionLeadOpportunities = cuLeadOpportunityRepository.findByCreditUnionId(creditUnionId);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // delete all older data of same credit union
        cuLeadOpportunityRepository.deleteByCreditUnionId(creditUnionId);
        return objectMapper.convertValue(creditUnionLeadOpportunities, new TypeReference<List<LeadsAndOpportunitiesGetResponseBody>>() {
        });
    }
    @Transactional
    public List<LeadsAndOpportunitiesGetResponseBody> getActionedLeadsOpportunities(String creditUnionId) {
        List<CreditUnionLeadOpportunity> actionedCreditUnionLeadOpportunities = cuLeadOpportunityRepository.findByCreditUnionIdAndBocforgeMemberResponseNotNull(creditUnionId);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // delete all older data of same credit union
        cuLeadOpportunityRepository.deleteByCreditUnionIdAndBocforgeMemberResponseNotNull(creditUnionId);
        return objectMapper.convertValue(actionedCreditUnionLeadOpportunities, new TypeReference<List<LeadsAndOpportunitiesGetResponseBody>>() {});
    }



    public List<CreditUnionLeadOpportunity> findAllLeadsOpportunities(List<CreditUnionLeadOpportunity> newCreditUnionLeadsOpportunitiesList){
        List<CreditUnionLeadOpportunity> creditUnionLeadOpportunities= new ArrayList<CreditUnionLeadOpportunity>();
        newCreditUnionLeadsOpportunitiesList.forEach(item -> {
            CreditUnionLeadOpportunity creditUnionLeadOpportunity=cuLeadOpportunityRepository.findByLeadsOpportunitiesId(item.getLeadsOpportunitiesId());
            if (creditUnionLeadOpportunity != null) {
                creditUnionLeadOpportunities.add(creditUnionLeadOpportunity);
            }
        });
        return creditUnionLeadOpportunities;
    }

    @Transactional
    public LeadsAndOpportunitiesPostResponseBody saveLeadsOpportunities(LeadsAndOpportunitiesPostRequestBody leadsAndOpportunitiesPostRequestBody) {
        List<LeadOpportunityList> lstLeadsAndOpportunities=leadsAndOpportunitiesPostRequestBody.getLeadOpportunityList();
        List<CreditUnionLeadOpportunity> newCreditUnionLeadsOpportunitiesList = objectMapper.convertValue(lstLeadsAndOpportunities,
                new TypeReference<List<CreditUnionLeadOpportunity>>() {
                });

        List<CreditUnionLeadOpportunity> alreadyExistRecord =findAllLeadsOpportunities(newCreditUnionLeadsOpportunitiesList);

        newCreditUnionLeadsOpportunitiesList.removeAll(alreadyExistRecord);

        newCreditUnionLeadsOpportunitiesList.forEach(item -> {
            item.setCreateDate(new Date());
            item.setModifyDate(new Date());
            item.setCreditUnionId(leadsAndOpportunitiesPostRequestBody.getCreditUnionId());
        });

       // Save the records into the Database
        if(newCreditUnionLeadsOpportunitiesList.size()>0)
            cuLeadOpportunityRepository.save(newCreditUnionLeadsOpportunitiesList);

        LeadsAndOpportunitiesPostResponseBody leadsAndOpportunitiesPostResponseBody = new LeadsAndOpportunitiesPostResponseBody();

        List<Record> records= new ArrayList<Record>();
        for(int len=0; len<alreadyExistRecord.size(); len++){
            Record record = new Record();
            record.setLeadOpportunityId(alreadyExistRecord.get(len).getLeadsOpportunitiesId());
            record.setMemberId(alreadyExistRecord.get(len).getBocforgeMemberNumber());
            records.add(record);
        }
        leadsAndOpportunitiesPostResponseBody.setRecords(records);
        leadsAndOpportunitiesPostResponseBody.setTotalCount(new Integer(newCreditUnionLeadsOpportunitiesList.size()));
        leadsAndOpportunitiesPostResponseBody.setFailureCount(new Integer(records.size()));
        return leadsAndOpportunitiesPostResponseBody;
    }


    public List<CreditUnionLeadOpportunity> removeDuplicateRecords(List<CreditUnionLeadOpportunity> newCreditUnionLeadsOpportunitiesList, Set<String> leadsOpportunityIds){

        newCreditUnionLeadsOpportunitiesList.removeIf(ne -> leadsOpportunityIds.contains(ne.getLeadsOpportunitiesId()));
        return newCreditUnionLeadsOpportunitiesList;
    }

    @Override
    public List<LeadOpportunityList> getLeadsOpportunitiesByMemberNumber(MemberLeadsOpportunitiesPostRequestBody memberLeadsOpportunitiesPostRequestBody) {
        String creditUnionId = memberLeadsOpportunitiesPostRequestBody.getCreditUnionId();
        String memberNumber = memberLeadsOpportunitiesPostRequestBody.getMemberNumber();
        List<CreditUnionLeadOpportunity> creditUnionLeadOpportunities = cuLeadOpportunityRepository.findByCreditUnionIdAndBocforgeMemberNumber(creditUnionId, memberNumber);
        return objectMapper.convertValue(creditUnionLeadOpportunities, new TypeReference<List<LeadOpportunityList>>() {});
    }

    public LeadOpportunityIdPutResponseBody updateLeadsOpportunities(LeadOpportunityIdPutRequestBody leadOpportunityIdPutRequestBody,
                                                                     String leadsOpportunityId) {
        LeadOpportunityIdPutResponseBody leadOpportunityIdPutResponseBody = new LeadOpportunityIdPutResponseBody();
        CreditUnionLeadOpportunity creditUnionLeadOpportunity = cuLeadOpportunityRepository.findByLeadsOpportunitiesId(leadsOpportunityId);

        if (creditUnionLeadOpportunity == null) {
            leadOpportunityIdPutResponseBody.setMessage(Constants.MESSAGE_NOT_UPDATED);
            return leadOpportunityIdPutResponseBody;
        }
        creditUnionLeadOpportunity.setLeadsOpportunitiesId(leadsOpportunityId);
        creditUnionLeadOpportunity.setBocforgeMemberResponse(leadOpportunityIdPutRequestBody.getBocforgeMemberResponseC());
        creditUnionLeadOpportunity.setBocforgeMemberChoiceText(leadOpportunityIdPutRequestBody.getBocforgeMemberChoiceTextC());
        creditUnionLeadOpportunity.setBocforgeButtonCode(leadOpportunityIdPutRequestBody.getBocforgeButtonCodeC());
        creditUnionLeadOpportunity.setBocforgeChoiceCode(leadOpportunityIdPutRequestBody.getBocforgeChoiceCodeC());
        creditUnionLeadOpportunity.setBocforgeMemberResponseDate(new Date());
        creditUnionLeadOpportunity.setGetBocforgeMemberResponseTime(new Date());
        creditUnionLeadOpportunity.setModifyDate(new Date());
        cuLeadOpportunityRepository.save(creditUnionLeadOpportunity);
        leadOpportunityIdPutResponseBody.setMessage(Constants.MESSAGE_UPDATED);
        return leadOpportunityIdPutResponseBody;
    }
}
