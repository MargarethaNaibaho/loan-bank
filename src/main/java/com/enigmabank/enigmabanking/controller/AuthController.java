package com.enigmabank.enigmabanking.controller;

import com.enigmabank.enigmabanking.dto.request.LoginRequest;
import com.enigmabank.enigmabanking.dto.request.RegisterAdminAndStaffRequest;
import com.enigmabank.enigmabanking.dto.request.RegisterCustomerRequest;
import com.enigmabank.enigmabanking.dto.response.CommonResponse;
import com.enigmabank.enigmabanking.dto.response.LoginResponse;
import com.enigmabank.enigmabanking.dto.response.RegisterResponse;
import com.enigmabank.enigmabanking.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/signup/customer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registerCustomer(@RequestParam String firstName,
                                              @RequestParam String lastName,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
                                              @RequestParam String phone,
                                              @RequestParam MultipartFile profilePicture,
                                              @RequestParam String email,
                                              @RequestParam String password){
        RegisterCustomerRequest registerCustomerRequest = RegisterCustomerRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .phone(phone)
                .profilePicture(profilePicture)
                .email(email)
                .password(password)
                .build();
        RegisterResponse registerResponse = authService.registerCustomer(registerCustomerRequest);
        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .message("Successfully create new customer")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterAdminAndStaffRequest registerAdminAndStaffRequest){
        RegisterResponse registerResponse = authService.registerAdmin(registerAdminAndStaffRequest);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Successfully create new admin")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/signup/staff")
    public ResponseEntity<?> registerStaff(@RequestBody RegisterAdminAndStaffRequest registerAdminAndStaffRequest){
        RegisterResponse registerResponse = authService.registerStaff(registerAdminAndStaffRequest);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Successfully create new staff")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = authService.login(loginRequest);
        CommonResponse<LoginResponse> commonResponse = CommonResponse.<LoginResponse>builder()
                .message("Successfully login")
                .statusCode(HttpStatus.OK.value())
                .data(loginResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

}
