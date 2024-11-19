package com.enigmabank.enigmabanking.controller;

import com.enigmabank.enigmabanking.dto.request.NewLoanRequest;
import com.enigmabank.enigmabanking.dto.response.CommonResponse;
import com.enigmabank.enigmabanking.dto.response.NewLoanResponse;
import com.enigmabank.enigmabanking.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class LoanController {
    private final LoanService loanService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<?> createNewLoan(@RequestBody NewLoanRequest newLoanRequest){
        NewLoanResponse newLoanResponse = loanService.createNewLoan(newLoanRequest);
        CommonResponse<NewLoanResponse> commonResponse = CommonResponse.<NewLoanResponse>builder()
                .message("Successfully create new loan")
                .statusCode(HttpStatus.CREATED.value())
                .data(newLoanResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }
}
