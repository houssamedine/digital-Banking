package com.e_bank.E_Banking.entites;

import com.e_bank.E_Banking.enums.OperationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "ACCOUNT_OPERATIONS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Account_Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date_operation;
    private double amount;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bank")
    private Bank_Account bankAccount;

    private String description;

}
