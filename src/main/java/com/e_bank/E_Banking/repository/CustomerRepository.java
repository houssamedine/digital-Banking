package com.e_bank.E_Banking.repository;


import com.e_bank.E_Banking.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,String> {

    Optional<Customer> findCustomerByEmail(String email);


}
