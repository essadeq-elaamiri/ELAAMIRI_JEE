package com.elaamiri.ebankbackend.mappers;

import com.elaamiri.ebankbackend.dto.*;
import com.elaamiri.ebankbackend.entities.*;
import com.elaamiri.ebankbackend.entities.enumerations.AccountType;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BankMapperImp implements BankMapper{

    @Override
    //map Customer to CustomerDTO
    public CustomerDTO dtoFromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }


    @Override
    public Customer customerFromDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    @Override
    public CurrentAccountDTO dtoFromCurrentAccount(CurrentAccount currentAccount) {
        CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentAccountDTO);
        if(currentAccount.getCustomer() != null){
            currentAccountDTO.setCustomerDTO(dtoFromCustomer(currentAccount.getCustomer()));
        }
        return currentAccountDTO;
    }

    @Override
    public CurrentAccount currentAccountFromDTO(CurrentAccountDTO currentAccountDTO) {
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO, currentAccount);
        return currentAccount;
    }

    @Override
    public SavingAccountDTO dtoFromSavingAccount(SavingAccount savingAccount) {
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingAccountDTO);
        // le customer n'est pas chargé par defaut je pense car on utilise le mode LAZY.
        // je pense aussi que il ne va pas connaitre comment le mapper
        if(savingAccount.getCustomer() != null){
            savingAccountDTO.setCustomerDTO(dtoFromCustomer(savingAccount.getCustomer()));
        }

        return savingAccountDTO;
    }

    @Override
    public SavingAccount savingAccountFromDTO(SavingAccountDTO savingAccountDTO) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO, savingAccount);
        return savingAccount;

    }

    @Override
    public BankAccountDTO dtoFromBankAccount(BankAccount bankAccount) {
        BankAccountDTO bankAccountDTO = null;
        if(bankAccount instanceof SavingAccount){
            bankAccountDTO = dtoFromSavingAccount((SavingAccount) bankAccount);
            bankAccountDTO.setAccountType(AccountType.SAVING_ACCOUNT);
        }
        else if(bankAccount instanceof CurrentAccount){
            bankAccountDTO = dtoFromCurrentAccount((CurrentAccount) bankAccount);
            bankAccountDTO.setAccountType(AccountType.CURRENT_ACCOUNT);

        }
        return bankAccountDTO;
    }

    @Override
    public BankAccount bankAccountFromDTO(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = null;
        if (bankAccountDTO instanceof  SavingAccountDTO){
            bankAccount = savingAccountFromDTO((SavingAccountDTO) bankAccountDTO);

        }
        else if(bankAccountDTO instanceof  CurrentAccountDTO){
            bankAccount = currentAccountFromDTO((CurrentAccountDTO) bankAccountDTO);
        }
        return bankAccount;
    }

    @Override
    public AccountOperationDTO dtoFromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        if(accountOperation.getBankAccount() != null){
            accountOperationDTO.setBankAccountDTO(dtoFromBankAccount(accountOperation.getBankAccount()));
        }

        return accountOperationDTO;
    }
    @Override
    public AccountOperation accountOperationFromDTO(AccountOperationDTO accountOperationDTO){
        AccountOperation accountOperation = new AccountOperation();
        BeanUtils.copyProperties(accountOperationDTO, accountOperation);
        accountOperation.setBankAccount(bankAccountFromDTO(accountOperationDTO.getBankAccountDTO()));
        return accountOperation;
    }


}
