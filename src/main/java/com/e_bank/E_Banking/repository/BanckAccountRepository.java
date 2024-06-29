package com.e_bank.E_Banking.repository;

import com.e_bank.E_Banking.entites.Bank_Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanckAccountRepository extends JpaRepository<Bank_Account,String> {
}
