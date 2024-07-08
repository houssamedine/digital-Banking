package com.e_bank.E_Banking.mappers;

import com.e_bank.E_Banking.dtos.bankAccount.CurrentBankAccountDto;
import com.e_bank.E_Banking.dtos.bankAccount.SavingBankAccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    SavingBankAccountDto fromSavingBankAccountToBankAccountBankAccountResponseDto(SavingBankAccountDto savingBankAccountDto);
    SavingBankAccountDto fromSavingBankAccountResponseDtoToSavingBankAccount(SavingBankAccountDto savingBankAccountDto);

    CurrentBankAccountDto fromCurrentBankAccountToCurrentBankAccountResponseDto(CurrentBankAccountDto currentBankAccountDto);
    CurrentBankAccountDto fromCurrentBankAccountResponseDtoToCurrentBankAccountDto(CurrentBankAccountDto currentBankAccountDto);
}
