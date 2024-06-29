package com.e_bank.E_Banking.repository;

import com.e_bank.E_Banking.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
