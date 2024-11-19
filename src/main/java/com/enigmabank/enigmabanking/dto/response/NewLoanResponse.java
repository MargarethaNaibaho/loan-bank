package com.enigmabank.enigmabanking.dto.response;

import com.enigmabank.enigmabanking.entity.InstalmentType;
import com.enigmabank.enigmabanking.entity.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewLoanResponse {
    private String loanId;
    private String customerId;
    private LoanType loanType;
    private InstalmentType instalmentType;
    private Double nominal;
    private Long createdAt;
    private Long updatedAt;
}
