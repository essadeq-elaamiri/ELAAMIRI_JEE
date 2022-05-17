package com.elaamiri.ebankbackend.services;

import com.elaamiri.ebankbackend.entities.BankAccount;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.entities.enumerations.AccountType;
import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import com.elaamiri.ebankbackend.exceptions.AccountNotFoundException;
import com.elaamiri.ebankbackend.exceptions.CustomerNotFoundException;
import com.elaamiri.ebankbackend.repositories.BankAccountRepository;
import com.elaamiri.ebankbackend.repositories.CustomerRepository;
import com.elaamiri.ebankbackend.services.interfaces.BankAccountService;
import com.elaamiri.ebankbackend.services.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j // get log attribute (by lombok)
public class BankAccountServiceImp implements BankAccountService {
    BankAccountRepository bankAccountRepository;
    CustomerService customerService;

    //Logger log = LoggerFactory.getLogger(this.getClass().getName()); // done by lombok

    @Override
    public BankAccount saveAccount(double initBalance, AccountType accountType, String customerId) {
        log.info("Saving account ...");
        Customer customer = customerService.getCustomerById(customerId);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setBalance(initBalance);  // must be validated
        bankAccount.setCustomer(customer);
        bankAccount.setCreatedAt(new Date());
        return null;
    }

    @Override
    public BankAccount saveAccount(BankAccount bankAccount) {
        log.info("Saving account ....");
        if(bankAccount == null) throw new AccountNotFoundException("Invalid account [NULL]");
        bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public boolean deleteAccount(String accountId) {
        return false;
    }

    @Override
    public BankAccount updateAccount(String accountId, BankAccount bankAccount) {
        log.info("Updating customer ....");
        Customer customer =  getCustomerById(customerId);
        return customerRepository.save(customer);
    }

    @Override
    public List<BankAccount> getBankAccountsList(int page, int size) {
        return null;
    }

    @Override
    public BankAccount getBankAccountById(String accountId) {
        log.info("Selecting an account ....");
        return bankAccountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(null));
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
