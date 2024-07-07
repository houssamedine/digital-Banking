package com.e_bank.E_Banking.mappers;

import com.e_bank.E_Banking.dtos.customer.CustomerRequestDto;
import com.e_bank.E_Banking.dtos.customer.CustomerResponseDto;
import com.e_bank.E_Banking.entites.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDto fromCustomerToCustomerResponseDto(Customer customer);
    Customer fromCustomerResponseDtoToCustomer(CustomerRequestDto customerRequestDto);
//    public CustomerResponseDto fromCustomerToCustomerResponseDto(Customer customer) {
//        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
//
//        //1ére Méthod
//        BeanUtils.copyProperties(customer,customerResponseDto);
//
//        /*2éme méthod
//        customerResponseDto.setId_customer(customer.getId_customer());
//        customerResponseDto.setFirstName(customer.getFirstName());
//        customerResponseDto.setLastName(customer.getLastName());
//        customerResponseDto.setEmail(customer.getEmail());
//        customerResponseDto.setPhoto(customer.getPhoto());
//        return customerResponseDto;
//         */
//        return customerResponseDto;
//    }
//
//    public Customer fromCustomerResponseDtoToCustomer(CustomerResponseDto customerResponseDto) {
//        Customer customer = new Customer();
//        BeanUtils.copyProperties(customerResponseDto,customer);
//        return customer;
//    }



}
