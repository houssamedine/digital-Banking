package com.e_bank.E_Banking.web;

import com.e_bank.E_Banking.entites.Customer;
import com.e_bank.E_Banking.services.Customer.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/")
    List<Customer> getCustomers() {
        return customerService.lisCustomers();
    }

}
