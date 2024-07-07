package com.e_bank.E_Banking.services.Customer;

import com.e_bank.E_Banking.Exceptions.CustomerNotFoundException;
import com.e_bank.E_Banking.dtos.customer.CustomerRequestDto;
import com.e_bank.E_Banking.dtos.customer.CustomerResponseDto;
import com.e_bank.E_Banking.entites.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto saveCustomer(CustomerRequestDto customerRequestDto);
    CustomerResponseDto updateCustomer(CustomerRequestDto customerRequestDto,String id);
    CustomerResponseDto getCustomerById(String id) throws CustomerNotFoundException;
    CustomerResponseDto getCustomerByEmail(String email) throws CustomerNotFoundException;;
    List<CustomerResponseDto> lisCustomers();
    boolean deleteCustomerById(String id);
}
