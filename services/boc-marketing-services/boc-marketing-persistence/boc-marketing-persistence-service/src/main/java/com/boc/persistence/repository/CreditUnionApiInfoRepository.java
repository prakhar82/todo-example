package com.boc.persistence.repository;

import com.boc.persistence.model.CreditUnionApiInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditUnionApiInfoRepository extends JpaRepository<CreditUnionApiInfo, Integer> {
    CreditUnionApiInfo findByCuIdAndOperation(String creditUnionId, String operation);
    List<CreditUnionApiInfo> findByCuId(String creditUnionId);
}
