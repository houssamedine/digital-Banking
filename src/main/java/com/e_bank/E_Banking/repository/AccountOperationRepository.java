package com.e_bank.E_Banking.repository;

import com.e_bank.E_Banking.entites.Account_Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<Account_Operation,Long> {
}
