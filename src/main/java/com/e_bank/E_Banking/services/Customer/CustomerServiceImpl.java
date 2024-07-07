package com.e_bank.E_Banking.services.Customer;

import com.e_bank.E_Banking.Exceptions.CustomerNotFoundException;
import com.e_bank.E_Banking.dtos.customer.CustomerRequestDto;
import com.e_bank.E_Banking.dtos.customer.CustomerResponseDto;
import com.e_bank.E_Banking.entites.Customer;
import com.e_bank.E_Banking.mappers.CustomerMapper;
import com.e_bank.E_Banking.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j               //permet de faire la journalisation et logger les messages
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto saveCustomer(CustomerRequestDto customerRequestDto) {
        log.info("Saving customer");

        // Convert DTO to entity
        Customer customer = customerMapper.fromCustomerResponseDtoToCustomer(customerRequestDto);

        // Check if a customer with the same email already exists
        if (customerRepository.findCustomerByEmail(customer.getEmail()).isPresent()) {
            log.error("Customer already exists with email: {}", customer.getEmail());
            throw new IllegalArgumentException("Customer already exists with this email");
        }

        // Save the new customer
        Customer savedCustomer = customerRepository.save(customer);
        CustomerResponseDto customerResponseDto = customerMapper.fromCustomerToCustomerResponseDto(savedCustomer);

        log.info("Customer saved successfully with ID: {}", savedCustomer.getId_customer());
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto updateCustomer(CustomerRequestDto customerRequestDto, String id) {
        log.info("Updating customer with ID: {}", id);

        // Retrieve the existing customer from the database
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));

        // Update the existing customer entity with new information from the DTO
        existingCustomer.setFirstName(customerRequestDto.getFirstName());
        existingCustomer.setLastName(customerRequestDto.getLastName());
        existingCustomer.setEmail(customerRequestDto.getEmail());
        existingCustomer.setPhoto(customerRequestDto.getPhoto());

        // Save the updated customer entity
        Customer savedCustomer = customerRepository.save(existingCustomer);

        // Convert the updated customer entity to response DTO
        CustomerResponseDto customerResponseDto = customerMapper.fromCustomerToCustomerResponseDto(savedCustomer);

        log.info("Customer updated successfully with ID: {}", savedCustomer.getId_customer());
        return customerResponseDto;
    }



    @Override
    public CustomerResponseDto getCustomerById(String id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return customerMapper.fromCustomerToCustomerResponseDto(customer);
    }


    @Override
    public CustomerResponseDto getCustomerByEmail(String email) throws CustomerNotFoundException{
        Customer customer = customerRepository.findCustomerByEmail(email).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return customerMapper.fromCustomerToCustomerResponseDto(customer);
    }

    @Override
    public List<CustomerResponseDto> lisCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtos = customers.stream().map(
                cust -> customerMapper.fromCustomerToCustomerResponseDto(cust)).collect(Collectors.toList());

        return customerResponseDtos;
    }

    @Override
    public boolean deleteCustomerById(String id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}