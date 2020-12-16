package com.boc.persistence.repository;

import com.boc.persistence.model.CreditUnionAuditInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface CreditUnionAuditInfoRepository extends JpaRepository<CreditUnionAuditInfo, Integer> {

    List<CreditUnionAuditInfo> findByCreditUnionIdAndCreateDateBetween(String creditUnionId, Date startDate, Date endDate);
    List<CreditUnionAuditInfo> findByCreditUnionIdAndCreateDateBetweenAndOperationResultNot(String creditUnionId, Date startDate, Date endDate, String operationResult);
    List<CreditUnionAuditInfo> findByCreditUnionId(String creditUnionId);
    @Modifying
    @Transactional
    public void deleteByCreateDateBefore(Date expiryDate);
}
