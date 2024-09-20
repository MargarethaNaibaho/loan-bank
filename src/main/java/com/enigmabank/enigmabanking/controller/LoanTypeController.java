package com.enigmabank.enigmabanking.controller;

import com.enigmabank.enigmabanking.dto.request.LoanTypeRequest;
import com.enigmabank.enigmabanking.dto.response.CommonResponse;
import com.enigmabank.enigmabanking.entity.InstalmentType;
import com.enigmabank.enigmabanking.entity.LoanType;
import com.enigmabank.enigmabanking.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-types")
@RequiredArgsConstructor
public class LoanTypeController {
    private final LoanTypeService loanTypeService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @PostMapping
    public ResponseEntity<?> createNewLoanType(@RequestBody LoanTypeRequest loanTypeRequest){
        LoanType loanType = loanTypeService.createLoanType(loanTypeRequest);
        CommonResponse<LoanType> commonResponse = CommonResponse.<LoanType>builder()
                .message("Successfully create new loan type")
                .statusCode(HttpStatus.OK.value())
                .data(loanType)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanTypeById(@PathVariable String id){
        LoanType loanType = loanTypeService.getLoanTypeById(id);
        CommonResponse<LoanType> commonResponse = CommonResponse.<LoanType>builder()
                .message("Successfully get loan type")
                .statusCode(HttpStatus.OK.value())
                .data(loanType)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllLoanTypes(){
        List<LoanType> loanTypes = loanTypeService.getAllLoanTypes();
        CommonResponse<List<LoanType>> commonResponse = CommonResponse.<List<LoanType>>builder()
                .message("Successfully fetch loan types")
                .statusCode(HttpStatus.OK.value())
                .data(loanTypes)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    @PutMapping
    public ResponseEntity<?> updateLoanType(@RequestBody LoanType loanTypeRequest){
        LoanType loanTypeUpdated = loanTypeService.updateLoanTypes(loanTypeRequest);
        CommonResponse<LoanType> commonResponse = CommonResponse.<LoanType>builder()
                .message("Succesfully update loan type")
                .statusCode(HttpStatus.OK.value())
                .data(loanTypeUpdated)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoanType(@PathVariable String id){
        loanTypeService.deleteLoanTypes(id);
        CommonResponse<?> commonResponse = CommonResponse.builder()
                .message("Successfully delete loan type by id")
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
