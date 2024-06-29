package com.e_bank.E_Banking.services;

import com.e_bank.E_Banking.entites.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer getCustomerById(Long id);
    Customer getCustomerByEmail(String email);
    List<Customer> lisCustomers();
    void deleteCustomerById(Long id);
}
