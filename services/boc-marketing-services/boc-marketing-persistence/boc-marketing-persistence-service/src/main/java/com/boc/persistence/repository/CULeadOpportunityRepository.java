package com.boc.persistence.repository;

import com.backbase.persistence.rest.spec.v1.leadsopportunities.*;
import com.boc.persistence.model.CreditUnionLeadOpportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CULeadOpportunityRepository extends JpaRepository<CreditUnionLeadOpportunity, String> {

    List<CreditUnionLeadOpportunity> findByCreditUnionId(String creditUnionId);

    CreditUnionLeadOpportunity findByLeadsOpportunitiesId(String leadsOpportunitiesId);

    void deleteByCreditUnionId(String creditUnionId);

    List<CreditUnionLeadOpportunity> findByCreditUnionIdAndBocforgeMemberResponseNotNull(String creditUnionId);

    void deleteByCreditUnionIdAndBocforgeMemberResponseNotNull(String creditUnionId);

    List<CreditUnionLeadOpportunity> findByCreditUnionIdAndBocforgeMemberNumber(String creditUnionId, String memberNumber);

}
