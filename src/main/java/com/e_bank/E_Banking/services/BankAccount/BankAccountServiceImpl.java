package com.e_bank.E_Banking.services.BankAccount;

import com.e_bank.E_Banking.Exceptions.BalanceNotSufficientException;
import com.e_bank.E_Banking.Exceptions.BankAccountNotFoundException;
import com.e_bank.E_Banking.Exceptions.CustomerNotFoundException;
import com.e_bank.E_Banking.entites.*;
import com.e_bank.E_Banking.enums.OperationType;
import com.e_bank.E_Banking.repository.AccountOperationRepository;
import com.e_bank.E_Banking.repository.BanckAccountRepository;
import com.e_bank.E_Banking.services.Customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@Slf4j              //permet de faire la journalisation et logger les messages
public class BankAccountServiceImpl implements BankAccountService {

    private BanckAccountRepository banckAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private CustomerService customerService;

    public BankAccountServiceImpl(BanckAccountRepository banckAccountRepository, AccountOperationRepository accountOperationRepository, CustomerService customerService) {
        this.banckAccountRepository = banckAccountRepository;
        this.accountOperationRepository = accountOperationRepository;
        this.customerService = customerService;
    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, String customerId) {
        Customer customer= customerService.getCustomerById(customerId);
        if(customer == null){
            throw  new CustomerNotFoundException("Customer not found !!");
        }
        CurrentAccount currentBankAccount= new CurrentAccount();

        currentBankAccount.setId_bank(UUID.randomUUID().toString());
        currentBankAccount.setCreatedAt(new Date());
        currentBankAccount.setBalance(initialBalance);
        currentBankAccount.setOuverDraft(overDraft);
        currentBankAccount.setCustomer(customer);
        CurrentAccount saveCurrAccount = banckAccountRepository.save(currentBankAccount);
        return saveCurrAccount;
    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, String customerId) {
        Customer customer= customerService.getCustomerById(customerId);
        if(customer == null){
            throw  new CustomerNotFoundException("Customer not found !!");
        }

        SavingAccount savingBankAccount = new SavingAccount();

        savingBankAccount.setId_bank(UUID.randomUUID().toString());
        savingBankAccount.setCreatedAt(new Date());
        savingBankAccount.setBalance(initialBalance);
        savingBankAccount.setInterestRate(interestRate);
        savingBankAccount.setCustomer(customer);
        SavingAccount saveCurrAccount = banckAccountRepository.save(savingBankAccount);
        return saveCurrAccount;
    }


    @Override
    public Bank_Account getBankAccountById(String id) {
        Bank_Account bankAccount=banckAccountRepository.findById(id).orElseThrow(()-> new BankAccountNotFoundException("Account Not Found !!"));
        return bankAccount;
    }

    @Override
    public void debitAccount(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        Bank_Account bankAccount= getBankAccountById(accountId);

        if(bankAccount.getBalance()<amount)
            throw  new BalanceNotSufficientException("Balance Not Sufficient !!");

        Account_Operation accountOperation= new Account_Operation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDate_operation(new Date());
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        banckAccountRepository.save(bankAccount);
    }

    @Override
    public void creditAccount(String accountId, double amount, String description) throws BankAccountNotFoundException {
        Bank_Account bankAccount= getBankAccountById(accountId);

        Account_Operation accountOperation= new Account_Operation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDate_operation(new Date());
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        banckAccountRepository.save(bankAccount);
    }

    @Override
    public void transferToAccount(String accountIdSource, String accountIdDestination, double amount,String description) throws BalanceNotSufficientException,BankAccountNotFoundException {
        debitAccount(accountIdSource,amount,description +" to "+accountIdDestination);
        creditAccount(accountIdDestination,amount,description+ " from "+accountIdSource );
    }

    @Override
    public List<Bank_Account> bankAccountList(){
        return banckAccountRepository.findAll();
    }

}
