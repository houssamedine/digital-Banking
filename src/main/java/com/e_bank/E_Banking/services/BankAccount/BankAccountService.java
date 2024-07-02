package com.e_bank.E_Banking.services.BankAccount;

import com.e_bank.E_Banking.Exceptions.BalanceNotSufficientException;
import com.e_bank.E_Banking.Exceptions.BankAccountNotFoundException;
import com.e_bank.E_Banking.entites.Bank_Account;
import com.e_bank.E_Banking.entites.CurrentAccount;
import com.e_bank.E_Banking.entites.SavingAccount;

import java.util.List;

public interface BankAccountService {
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, String customerId);
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, String customerId);
    Bank_Account getBankAccountById(String id);
    void debitAccount(String accountId, double amount, String description) throws com.e_bank.E_Banking.Exceptions.BalanceNotSufficientException;
    void creditAccount(String accountId,double amount, String description) throws BalanceNotSufficientException;
    void transferToAccount(String accountIdSource, String accountIdDestination, double amount,String description) throws BalanceNotSufficientException, BankAccountNotFoundException;
    List<Bank_Account> bankAccountList();

}
