package com.enigmabank.enigmabanking.service;

import com.enigmabank.enigmabanking.dto.request.LoanTypeRequest;
import com.enigmabank.enigmabanking.entity.LoanType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanTypeService {
    LoanType createLoanType(LoanTypeRequest loanTypeRequest);
    LoanType getLoanTypeById(String id);
    List<LoanType> getAllLoanTypes();
    LoanType updateLoanTypes(LoanType loanType);
    void deleteLoanTypes(String id);
}
