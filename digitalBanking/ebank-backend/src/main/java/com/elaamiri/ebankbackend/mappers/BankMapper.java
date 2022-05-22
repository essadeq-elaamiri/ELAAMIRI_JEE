package com.elaamiri.ebankbackend.mappers;

import com.elaamiri.ebankbackend.dto.*;
import com.elaamiri.ebankbackend.entities.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface BankMapper {
    //map Customer to CustomerDTO
    CustomerDTO dtoFromCustomer(Customer customer);
    Customer customerFromDTO(CustomerDTO customerDTO);

    // mapping BankAccount
    CurrentAccountDTO dtoFromCurrentAccount(CurrentAccount currentAccount);
    CurrentAccount currentAccountFromDTO(CurrentAccountDTO currentAccountDTO);

    SavingAccountDTO dtoFromSavingAccount(SavingAccount savingAccount);
    SavingAccount savingAccountFromDTO(SavingAccountDTO savingAccountDTO);

    BankAccountDTO dtoFromBankAccount(BankAccount bankAccount);
    BankAccount bankAccountFromDTO(BankAccountDTO bankAccountDTO);

    AccountOperationDTO dtoFromAccountOperation(AccountOperation accountOperation);

    AccountOperation accountOperationFromDTO(AccountOperationDTO accountOperationDTO);
}
