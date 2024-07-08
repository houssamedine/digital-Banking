package com.e_bank.E_Banking.dtos.bankAccount;

import com.e_bank.E_Banking.dtos.customer.CustomerResponseDto;
import com.e_bank.E_Banking.enums.AccountStatus;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CurrentBankAccountDto {
    private String id_bank;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    private CustomerResponseDto customer;
    private double ouverDraft;
}
