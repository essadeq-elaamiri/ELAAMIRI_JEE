package com.elaamiri.ebankbackend.mappers;

import com.elaamiri.ebankbackend.dto.BankAccountDTO;
import com.elaamiri.ebankbackend.dto.CurrentAccountDTO;
import com.elaamiri.ebankbackend.dto.CustomerDTO;
import com.elaamiri.ebankbackend.dto.SavingAccountDTO;
import com.elaamiri.ebankbackend.entities.BankAccount;
import com.elaamiri.ebankbackend.entities.CurrentAccount;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.entities.SavingAccount;
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
        currentAccountDTO.setCustomerDTO(dtoFromCustomer(currentAccount.getCustomer()));
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
        savingAccountDTO.setCustomerDTO(dtoFromCustomer(savingAccount.getCustomer()));
        return savingAccountDTO;
    }

    @Override
    public SavingAccount savingAccountFromDTO(SavingAccountDTO savingAccountDTO) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO, savingAccount);
        return savingAccount;

    }

    @Override
    public BankAccountDTO dtoFromSavingAccount(BankAccount bankAccount) {
        BankAccountDTO bankAccountDTO = null;
        if(bankAccountDTO instanceof SavingAccountDTO){
            bankAccountDTO = new SavingAccountDTO();
            BeanUtils.copyProperties(bankAccount, bankAccountDTO);
            ((SavingAccountDTO) bankAccountDTO).setInterestRate(((SavingAccountDTO) bankAccountDTO).getInterestRate());

        }
        else if(bankAccountDTO instanceof CurrentAccountDTO){
            bankAccountDTO = new CurrentAccountDTO();
            BeanUtils.copyProperties(bankAccount, bankAccountDTO);
            ((CurrentAccountDTO) bankAccountDTO).setOverDraft(((CurrentAccountDTO) bankAccountDTO).getOverDraft());
        }
        bankAccountDTO.setCustomerDTO(dtoFromCustomer(bankAccount.getCustomer()));
        return bankAccountDTO;
    }

    @Override
    public BankAccount savingAccountFromDTO(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = null;
        if (bankAccountDTO.getAccountType() == AccountType.SAVING_ACCOUNT){
            bankAccount = new SavingAccount();
            BeanUtils.copyProperties(bankAccountDTO, Objects.requireNonNull(bankAccount));
            ((SavingAccount)bankAccount).setInterestRate(((SavingAccount) bankAccount).getInterestRate());
        }
        else if(bankAccountDTO.getAccountType() == AccountType.CURRENT_ACCOUNT){
            bankAccount = new CurrentAccount();
            BeanUtils.copyProperties(bankAccountDTO, Objects.requireNonNull(bankAccount));
            ((CurrentAccount)bankAccount).setOverDraft(((CurrentAccount) bankAccount).getOverDraft());
        }
        bankAccount.setCustomer(customerFromDTO(bankAccountDTO.getCustomerDTO()));
        return bankAccount;
    }


}
