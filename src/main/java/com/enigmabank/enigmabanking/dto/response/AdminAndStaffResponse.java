package com.enigmabank.enigmabanking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminAndStaffResponse {
    private String id;
    private String email;
    private String role;
}
