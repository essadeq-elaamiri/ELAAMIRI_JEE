package com.elaamiri.ebankbackend.mappers;

import com.elaamiri.ebankbackend.dto.BankAccountDTO;
import com.elaamiri.ebankbackend.dto.CurrentAccountDTO;
import com.elaamiri.ebankbackend.dto.CustomerDTO;
import com.elaamiri.ebankbackend.dto.SavingAccountDTO;
import com.elaamiri.ebankbackend.entities.BankAccount;
import com.elaamiri.ebankbackend.entities.CurrentAccount;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.entities.SavingAccount;
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

    BankAccountDTO dtoFromSavingAccount(BankAccount bankAccount);
    BankAccount savingAccountFromDTO(BankAccountDTO bankAccountDTO);

}
