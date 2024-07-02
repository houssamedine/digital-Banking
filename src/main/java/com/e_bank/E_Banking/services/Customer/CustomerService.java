package com.e_bank.E_Banking.services.Customer;

import com.e_bank.E_Banking.entites.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer getCustomerById(String id);
    Customer getCustomerByEmail(String email);
    List<Customer> lisCustomers();
    void deleteCustomerById(String id);
}
