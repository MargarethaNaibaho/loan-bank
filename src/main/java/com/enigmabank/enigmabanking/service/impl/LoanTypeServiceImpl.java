package com.enigmabank.enigmabanking.service.impl;

import com.enigmabank.enigmabanking.dto.request.LoanTypeRequest;
import com.enigmabank.enigmabanking.entity.InstalmentType;
import com.enigmabank.enigmabanking.entity.LoanType;
import com.enigmabank.enigmabanking.repository.LoanTypeRepository;
import com.enigmabank.enigmabanking.service.LoanTypeService;
import com.enigmabank.enigmabanking.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoanType createLoanType(LoanTypeRequest loanTypeRequest) {
        try{
            validationUtil.validate(loanTypeRequest);

            LoanType loanType = LoanType.builder()
                    .type(loanTypeRequest.getType())
                    .maxLoan(loanTypeRequest.getMaxLoan())
                    .build();
            return loanTypeRepository.saveAndFlush(loanType);
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Loan type already exists!");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public LoanType getLoanTypeById(String id) {
        return loanTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan type isn't found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanType> getAllLoanTypes() {
        List<LoanType> loanTypes = loanTypeRepository.findAll();
        if(loanTypes.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no loan types in database");
        }

        return loanTypes;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoanType updateLoanTypes(LoanType loanTypeRequest) {
        validationUtil.validate(loanTypeRequest);
        LoanType existingLoanType = loanTypeRepository.findById(loanTypeRequest.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer isn't found"));

        if(loanTypeRepository.existsByType(loanTypeRequest.getType())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Loan type already exists!");
        }

        if(loanTypeRequest.getMaxLoan() != null){
            existingLoanType.setMaxLoan(loanTypeRequest.getMaxLoan());
        }
        if(loanTypeRequest.getType() != null){
            existingLoanType.setType(loanTypeRequest.getType());
        }

        return loanTypeRepository.saveAndFlush(existingLoanType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteLoanTypes(String id) {
        Optional<LoanType> optionalLoanType = loanTypeRepository.findById(id);
        if(optionalLoanType.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan type isn't found");
        }

        loanTypeRepository.deleteById(id);
    }
}
