package com.enigmabank.enigmabanking.service;

import com.enigmabank.enigmabanking.dto.request.LoginRequest;
import com.enigmabank.enigmabanking.dto.request.RegisterAdminAndStaffRequest;
import com.enigmabank.enigmabanking.dto.request.RegisterCustomerRequest;
import com.enigmabank.enigmabanking.dto.response.LoginResponse;
import com.enigmabank.enigmabanking.dto.response.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    RegisterResponse registerCustomer(RegisterCustomerRequest registerCustomerRequest);
    RegisterResponse registerAdmin(RegisterAdminAndStaffRequest registerAdminAndStaffRequest);
    RegisterResponse registerStaff(RegisterAdminAndStaffRequest registerAdminAndStaffRequest);
    LoginResponse login(LoginRequest loginRequest);
}
