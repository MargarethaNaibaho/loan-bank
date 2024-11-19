package com.enigmabank.enigmabanking.service.impl;

import com.enigmabank.enigmabanking.dto.request.CustomerRequest;
import com.enigmabank.enigmabanking.dto.response.CustomerResponse;
import com.enigmabank.enigmabanking.dto.response.FileResponse;
import com.enigmabank.enigmabanking.entity.Customer;
import com.enigmabank.enigmabanking.repository.CustomerRepository;
import com.enigmabank.enigmabanking.service.CustomerService;
import com.enigmabank.enigmabanking.service.ProfilePictureService;
import com.enigmabank.enigmabanking.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer createNewCustomer(Customer customer) {
        try{
            return customerRepository.saveAndFlush(customer);
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponse getCustomerById(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer isn't found"));
        if(customer.getStatus().equals(false)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer isn't found");
        }
        return mapToResponse(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getCustomerByIdNoResponse(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer isn't found"));
        if(customer.getStatus().equals(false)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer isn't found");
        }

        return customer;
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean isCustomerActive(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer isn't found"));
        return customer.getStatus();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAllByStatus(true);
        if (customers.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no customers in database");
        }

        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer : customers){
            CustomerResponse customerResponse = mapToResponse(customer);
            customerResponses.add(customerResponse);
        }

        return customerResponses;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
        validationUtil.validate(customerRequest);
        Customer existingCustomer = customerRepository.findById(customerRequest.getCustomerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer isn't found"));

        if (customerRequest.getFirstName() != null) {
            existingCustomer.setFirstName(customerRequest.getFirstName());
        }
        if (customerRequest.getLastName() != null) {
            existingCustomer.setLastName(customerRequest.getLastName());
        }
        if (customerRequest.getDateOfBirth() != null) {
            existingCustomer.setDateOfBirth(customerRequest.getDateOfBirth());
        }
        if (customerRequest.getPhone() != null) {
            existingCustomer.setPhone(customerRequest.getPhone());
        }
        if (customerRequest.getStatus() != null) {
            existingCustomer.setStatus(customerRequest.getStatus());
        }
        customerRepository.saveAndFlush(existingCustomer);

        return mapToResponse(existingCustomer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCustomer(String id) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer isn't found"));
        if(existingCustomer.getStatus().equals(false)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer isn't found");
        }
        existingCustomer.setStatus(false);
        customerRepository.saveAndFlush(existingCustomer);
    }

    private CustomerResponse mapToResponse(Customer customer){
        FileResponse fileResponse = FileResponse.builder()
                .filename(customer.getProfilePicture().getName())
                .url("/api/customers/" + customer.getId() + "/image")
                .build();

        return CustomerResponse.builder()
                .customerId(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dateOfBirth(customer.getDateOfBirth())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .profilePicture(fileResponse)
                .build();
    }
}
