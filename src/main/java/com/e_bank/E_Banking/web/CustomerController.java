package com.e_bank.E_Banking.web;

import com.e_bank.E_Banking.Exceptions.CustomerNotFoundException;
import com.e_bank.E_Banking.dtos.customer.CustomerRequestDto;
import com.e_bank.E_Banking.dtos.customer.CustomerResponseDto;
import com.e_bank.E_Banking.services.Customer.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<List<CustomerResponseDto>> getCustomers() {
        try {
            List<CustomerResponseDto> customers = customerService.lisCustomers();
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable String id) {
        try {
            CustomerResponseDto customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build(); // Retourne HTTP 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Retourne HTTP 500 Internal Server Error
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerResponseDto> getCustomerByEmail(@PathVariable String email) {
        try {
            CustomerResponseDto customer = customerService.getCustomerByEmail(email);
            return ResponseEntity.ok(customer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build(); // Retourne HTTP 404 Not Found

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Retourne HTTP 500 Internal Server Error
        }
    }


    @PostMapping()
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerRequestDto customerdto) {
        try {
            // Call the service method to save the customer
            CustomerResponseDto responseDto = customerService.saveCustomer(customerdto);

            log.info("Customer created successfully with ID: {}", responseDto.getId_customer());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        } catch (IllegalArgumentException e) {
            log.error("Error creating customer: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error creating customer: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating customer.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerRequestDto customerdto , @PathVariable String id) {
        try {
            // Call the service method to update the customer
            CustomerResponseDto responseDto = customerService.updateCustomer(customerdto,id);

            log.info("Customer updated successfully with ID: {}", responseDto.getId_customer());
            return ResponseEntity.ok(responseDto);

        } catch (CustomerNotFoundException e) {
            log.error("Error updating customer: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error updating customer: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating customer.");
        }
    }



    @DeleteMapping("/supp/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        try {
            if (customerService.deleteCustomerById(id)) {
                log.info("Customer with ID {} deleted successfully.", id);
                return ResponseEntity.ok("Customer deleted successfully.");
            } else {
                log.warn("Customer with ID {} not found.", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
            }
        } catch (Exception e) {
            log.error("Error deleting customer with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting customer.");
        }
    }


}
