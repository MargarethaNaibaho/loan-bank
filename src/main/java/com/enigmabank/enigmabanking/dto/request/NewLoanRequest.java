package com.enigmabank.enigmabanking.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewLoanRequest {
    @NotBlank(message = "Loan type is required")
    private String loanTypeId;

    @NotBlank(message = "Instalment type is required")
    private String instalmentTypeId;
}
