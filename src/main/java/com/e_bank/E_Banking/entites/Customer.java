package com.e_bank.E_Banking.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_customer;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Bank_Account> bankAccountsList;


}
