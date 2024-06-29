package com.e_bank.E_Banking.entites;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("CRA")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CurrentAccount extends Bank_Account {
    private double ouverDraft;
}
