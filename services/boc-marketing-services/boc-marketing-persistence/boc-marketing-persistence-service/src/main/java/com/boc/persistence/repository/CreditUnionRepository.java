package com.boc.persistence.repository;

import com.boc.persistence.model.CreditUnionAppAuth;
import com.boc.persistence.model.CreditUnionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditUnionRepository extends JpaRepository<CreditUnionInfo, String> {
    CreditUnionInfo findByCreditUnionId(String creditUnionId);
}
