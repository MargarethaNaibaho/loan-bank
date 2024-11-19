package com.enigmabank.enigmabanking.service.impl;

import com.enigmabank.enigmabanking.dto.request.NewLoanRequest;
import com.enigmabank.enigmabanking.dto.response.LoanResponse;
import com.enigmabank.enigmabanking.dto.response.NewLoanResponse;
import com.enigmabank.enigmabanking.entity.*;
import com.enigmabank.enigmabanking.repository.LoanRepository;
import com.enigmabank.enigmabanking.service.*;
import com.enigmabank.enigmabanking.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final LoanTypeService loanTypeService;
    private final InstalmentTypeService instalmentTypeService;
    private final CustomerService customerService;
    private final ValidationUtil validationUtil;
    private final UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public NewLoanResponse createNewLoan(NewLoanRequest newLoanRequest) {
        validationUtil.validate(newLoanRequest);
        //get user data
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = (AppUser) authentication.getPrincipal();

        User user = userService.getUserByIdNoResponse(appUser.getId());
        Customer customer = user.getCustomer();

        if(customer == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer information is missing");
        }

        LoanType loanType = loanTypeService.getLoanTypeById(newLoanRequest.getLoanTypeId());
        InstalmentType instalmentType = instalmentTypeService.getInstalmentTypeById(newLoanRequest.getInstalmentTypeId());

        Loan loan = Loan.builder()
                .loanType(loanType)
                .instalmentType(instalmentType)
                .customer(customer)
                .nominal(loanType.getMaxLoan())
                .build();

        loanRepository.saveAndFlush(loan);

        return NewLoanResponse.builder()
                .loanId(loan.getId())
                .customerId(loan.getCustomer().getId())
                .loanType(loanType)
                .instalmentType(instalmentType)
                .nominal(loanType.getMaxLoan())
                .createdAt(loan.getCreatedAt())
                .updatedAt(loan.getUpdatedAt())
                .build();
    }
}
