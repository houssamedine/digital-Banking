package com.e_bank.E_Banking;

import com.e_bank.E_Banking.Exceptions.BalanceNotSufficientException;
import com.e_bank.E_Banking.Exceptions.BankAccountNotFoundException;
import com.e_bank.E_Banking.Exceptions.CustomerNotFoundException;
import com.e_bank.E_Banking.dtos.customer.CustomerRequestDto;
import com.e_bank.E_Banking.dtos.customer.CustomerResponseDto;
import com.e_bank.E_Banking.entites.Bank_Account;
import com.e_bank.E_Banking.services.BankAccount.BankAccountService;
import com.e_bank.E_Banking.services.Customer.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingApplication {

    private static final Logger log = LoggerFactory.getLogger(EBankingApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EBankingApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService, CustomerService customerService) {
        return args -> {
            Stream.of("Hassan", "Imane", "Mohamed").forEach(name -> {
                CustomerRequestDto customer = new CustomerRequestDto();
                customer.setFirstName(name);
                customer.setLastName(name);
                customer.setEmail(name + "@gmail.com");
                log.info("Saving customer: {}", customer);
                customerService.saveCustomer(customer);
            });

            customerService.lisCustomers().forEach(
                    cust -> {
                        try {
                            log.info("Creating accounts for customer: {}", cust);
                            createAccountsAndPerformOperations(bankAccountService, cust);
                        } catch (CustomerNotFoundException | BankAccountNotFoundException cne) {
                            log.error("Exception: ", cne);
                        } catch (BalanceNotSufficientException e) {
                            log.error("Balance not sufficient: ", e);
                        }
                    }
            );
        };
    }

    @Transactional
    public void createAccountsAndPerformOperations(BankAccountService bankAccountService, CustomerResponseDto cust) throws CustomerNotFoundException, BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.saveCurrentBankAccount(Math.random() * 9000, 9000, cust.getId_customer());
        bankAccountService.saveSavingBankAccount(Math.random() * 12000, 5.5, cust.getId_customer());
        List<Bank_Account> bankAccountList = bankAccountService.bankAccountList();

        for (Bank_Account bank_account : bankAccountList) {
            log.info("Processing bank account: {}", bank_account);
            for (int i = 0; i < 10; i++) {
                bankAccountService.creditAccount(bank_account.getId_bank(), 10000 + Math.random() * 12000, "Crédit");
                bankAccountService.debitAccount(bank_account.getId_bank(), 1000 + Math.random() * 3000, "Débit");
            }
        }
    }
}
