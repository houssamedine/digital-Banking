package com.e_bank.E_Banking.services.Customer;

import com.e_bank.E_Banking.entites.Customer;
import com.e_bank.E_Banking.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j               //permet de faire la journalisation et logger les messages
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Saving customer");
        Optional<Customer> customerExist=customerRepository.findById(customer.getId_customer());
        if ((customerExist).isPresent()) {
            log.info("Customer already exists");
            return null;
        } else {
            return customerRepository.save(customer);
        }
    }


    @Override
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> customerExist=customerRepository.findById(customer.getId_customer());
        if (customerExist.isPresent()) {
            log.info("Updating customer");
            return customerRepository.save(customer);
        } else {
            log.info("Customer not found");
            return null;
        }
    }


    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).get();
    }


    @Override
    public Customer getCustomerByEmail(String email) {
        return null;
    }

    @Override
    public List<Customer> lisCustomers() {

        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomerById(String id) {

    }
}
