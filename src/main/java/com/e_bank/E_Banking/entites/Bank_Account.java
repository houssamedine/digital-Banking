package com.e_bank.E_Banking.entites;

import com.e_bank.E_Banking.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BANK_ACCOUNTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4,discriminatorType = DiscriminatorType.STRING)
public class Bank_Account {
    @Id
    private String id_bank;

    private Date createdAt;

    private double balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Customer")
    private Customer customer;

    @OneToMany(mappedBy = "bankAccount",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Account_Operation> accountOperations;
}
