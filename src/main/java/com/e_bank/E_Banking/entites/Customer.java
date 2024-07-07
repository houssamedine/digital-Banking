package com.e_bank.E_Banking.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_customer;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //c'est en utilise pas les DTO il faut ajouter ça pour ne pas tember dans une boucle infinier
    private List<Bank_Account> bankAccountsList;

}
