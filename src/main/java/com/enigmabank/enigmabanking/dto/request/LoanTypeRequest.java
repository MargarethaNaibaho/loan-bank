package com.enigmabank.enigmabanking.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTypeRequest {
    @NotBlank(message = "Loan type is required")
    private String type;

    @NotNull(message = "Max loan is required")
    @Min(value = 0, message = "Max loan must be greater than or equals zero")
    private Double maxLoan;
}
