package com.enigmabank.enigmabanking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String customerId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;
    private Boolean status;
    private FileResponse profilePicture;
}
