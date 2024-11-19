package com.enigmabank.enigmabanking.service;

import com.enigmabank.enigmabanking.dto.request.NewLoanRequest;
import com.enigmabank.enigmabanking.dto.response.LoanResponse;
import com.enigmabank.enigmabanking.dto.response.NewLoanResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoanService {
    NewLoanResponse createNewLoan(NewLoanRequest newLoanRequest);
}
