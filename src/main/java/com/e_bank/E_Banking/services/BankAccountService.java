package com.e_bank.E_Banking.services;

import com.e_bank.E_Banking.Exceptions.BalanceNotSufficientException;
import com.e_bank.E_Banking.entites.Bank_Account;
import com.e_bank.E_Banking.entites.CurrentAccount;
import com.e_bank.E_Banking.entites.SavingAccount;

public interface BankAccountService {
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId);
    SavingAccount saveSavingBankAccount(double initialBalance, double intersetRate, Long customerId);

    Bank_Account getBankAccountById(String id);
    void debitAccount(String accountId, double amount, String description) throws com.e_bank.E_Banking.Exceptions.BalanceNotSufficientException;
    void creditAccount(String accountId,double amount, String description) throws BalanceNotSufficientException;
    void transferToAccount(String accountIdSource, String accountIdDestination, double amount);
}
