package com.elaamiri.ebankbackend.services;

import com.elaamiri.ebankbackend.entities.BankAccount;
import com.elaamiri.ebankbackend.entities.enumerations.AccountType;
import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import com.elaamiri.ebankbackend.repositories.BankAccountRepository;
import com.elaamiri.ebankbackend.repositories.CustomerRepository;
import com.elaamiri.ebankbackend.services.interfaces.BankAccountService;
import com.elaamiri.ebankbackend.services.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankAccountServiceImp implements BankAccountService {
    BankAccountRepository bankAccountRepository;
    CustomerService customerService;

    @Override
    public BankAccount saveAccount(double initBalance, AccountType accountType, String customerId) {
        if(customerService.getCustomerById(customerId) == null)
        return ;
    }

    @Override
    public BankAccount saveAccount(BankAccount bankAccount) {
        return null;
    }

    @Override
    public boolean deleteAccount(String accountId) {
        return false;
    }

    @Override
    public BankAccount updateAccount(String accountId, BankAccount bankAccount) {
        return null;
    }

    @Override
    public List<BankAccount> getBankAccountsList(int page, int size) {
        return null;
    }

    @Override
    public BankAccount getBankAccountById(long accountId) {
        return null;
    }

    @Override
    public AccountType getAccountType(String accountId) {
        return null;
    }

    @Override
    public boolean applyOperation(String accountId, double amount, OperationType operationType, String description) {
        return false;
    }

    @Override
    public boolean debitAccount(String accountId, double amount, String description) {
        return false;
    }

    @Override
    public boolean creditAccount(String accountId, double amount, String description) {
        return false;
    }

    @Override
    public boolean transfer(String sourceAccount, String destinationAccount, boolean amountToTransfer) {
        return false;
    }
}
