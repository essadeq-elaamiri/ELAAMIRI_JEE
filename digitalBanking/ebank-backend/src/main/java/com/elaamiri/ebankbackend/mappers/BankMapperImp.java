package com.elaamiri.ebankbackend.mappers;

import com.elaamiri.ebankbackend.dto.CurrentAccountDTO;
import com.elaamiri.ebankbackend.dto.CustomerDTO;
import com.elaamiri.ebankbackend.dto.SavingAccountDTO;
import com.elaamiri.ebankbackend.entities.CurrentAccount;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.entities.SavingAccount;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        return savingAccountDTO;
    }

    @Override
    public SavingAccount savingAccountFromDTO(SavingAccountDTO savingAccountDTO) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO, savingAccount);
        return savingAccount;

    }


}
