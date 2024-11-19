package com.enigmabank.enigmabanking.repository;

import com.enigmabank.enigmabanking.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
}
