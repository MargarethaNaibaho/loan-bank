package com.enigmabank.enigmabanking.service;

import com.enigmabank.enigmabanking.dto.request.CustomerRequest;
import com.enigmabank.enigmabanking.dto.response.CustomerResponse;
import com.enigmabank.enigmabanking.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer createNewCustomer(Customer customer);
    CustomerResponse getCustomerById(String id);
    Customer getCustomerByIdNoResponse(String id);
    Boolean isCustomerActive(String id);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(CustomerRequest customerRequest);
    void deleteCustomer(String id);
}
