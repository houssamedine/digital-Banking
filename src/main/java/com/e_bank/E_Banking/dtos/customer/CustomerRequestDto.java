package com.e_bank.E_Banking.dtos.customer;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class CustomerRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
}
