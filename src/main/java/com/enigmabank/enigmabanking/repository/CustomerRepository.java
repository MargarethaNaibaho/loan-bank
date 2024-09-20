package com.enigmabank.enigmabanking.repository;

import com.enigmabank.enigmabanking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    List<Customer> findAllByStatus(Boolean status);
}
