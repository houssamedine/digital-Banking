package com.e_bank.E_Banking.web;

import com.e_bank.E_Banking.services.BankAccount.BankAccountService;
import com.e_bank.E_Banking.services.Customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankAccount")
@AllArgsConstructor
public class AccountBankController {

    private BankAccountService bankAccountService;
    private CustomerService customerService;

//    public Bank_Account saveAccount(Bank_Account bankAccount) {
//
//    }

}
