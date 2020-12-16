package com.boc.persistence.repository;

import com.boc.persistence.model.CreditUnionAppAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditUnionAppAuthRepository extends JpaRepository<CreditUnionAppAuth, Integer> {
    CreditUnionAppAuth findByCreditUnionId(String creditUnionId);
}
