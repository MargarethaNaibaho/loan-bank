package com.enigmabank.enigmabanking.repository;

import com.enigmabank.enigmabanking.entity.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, String> {
    LoanType findByType(String type);
    Boolean existsByType(String type);
}
