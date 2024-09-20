package com.enigmabank.enigmabanking.dto.request;

import com.enigmabank.enigmabanking.constant.EInstalmentType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstalmentTypeRequest {
    @NotBlank(message = "Instament type is required")
    private EInstalmentType instalmentType;
}
