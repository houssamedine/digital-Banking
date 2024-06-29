package com.e_bank.E_Banking.entites;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
@Entity
@DiscriminatorValue("CSA")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SavingAccount extends Bank_Account {
    private double interestRate;
}
