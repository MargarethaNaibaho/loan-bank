package com.enigmabank.enigmabanking.controller;

import com.enigmabank.enigmabanking.dto.request.CustomerRequest;
import com.enigmabank.enigmabanking.dto.response.CommonResponse;
import com.enigmabank.enigmabanking.dto.response.CustomerResponse;
import com.enigmabank.enigmabanking.dto.response.UserResponse;
import com.enigmabank.enigmabanking.entity.Customer;
import com.enigmabank.enigmabanking.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id){
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        CommonResponse<CustomerResponse> commonResponse = CommonResponse.<CustomerResponse>builder()
                .message("Successfully get customer by id")
                .statusCode(HttpStatus.OK.value())
                .data(customerResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getAllCustomers(){
        List<CustomerResponse> customerResponses = customerService.getAllCustomers();
        CommonResponse<List<CustomerResponse>> commonResponse = CommonResponse.<List<CustomerResponse>>builder()
                .message("Successfully fetch customers")
                .statusCode(HttpStatus.OK.value())
                .data(customerResponses)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody(required = false) CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.updateCustomer(customerRequest);
        CommonResponse<CustomerResponse> commonResponse = CommonResponse.<CustomerResponse>builder()
                .message("Successfully update a customer")
                .statusCode(HttpStatus.OK.value())
                .data(customerResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable String id){
        customerService.deleteCustomer(id);
        CommonResponse<CustomerResponse> commonResponse = CommonResponse.<CustomerResponse>builder()
                .message("Successfully delete customer by id")
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
