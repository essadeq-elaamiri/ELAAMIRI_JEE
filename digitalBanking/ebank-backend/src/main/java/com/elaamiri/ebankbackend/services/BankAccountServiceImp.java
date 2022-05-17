package com.elaamiri.ebankbackend.services;

import com.elaamiri.ebankbackend.entities.*;
import com.elaamiri.ebankbackend.entities.enumerations.AccountType;
import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import com.elaamiri.ebankbackend.exceptions.*;
import com.elaamiri.ebankbackend.repositories.BankAccountRepository;
import com.elaamiri.ebankbackend.repositories.CustomerRepository;
import com.elaamiri.ebankbackend.services.interfaces.AccountOperationService;
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
    AccountOperationService accountOperationService;

    //Logger log = LoggerFactory.getLogger(this.getClass().getName()); // done by lombok


    @Override
    public CurrentAccount saveCurrentAccount(double initBalance, double overDraft, String customerId) throws CustomerNotFoundException {
        log.info("Saving currentAccount ...");
        Customer customer = customerService.getCustomerById(customerId);
        CurrentAccount bankAccount =  new CurrentAccount();
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setBalance(initBalance);  // must be validated
        bankAccount.setCustomer(customer);
        bankAccount.setCreatedAt(new Date());
        bankAccount.setOverDraft(overDraft);
        return bankAccountRepository.save(bankAccount);

    }
    @Override
    public SavingAccount saveSavingAccount(double initBalance, double interestRate, String customerId) throws CustomerNotFoundException {
        log.info("Saving savingAccount ...");
        Customer customer = customerService.getCustomerById(customerId);
        SavingAccount bankAccount =  new SavingAccount();
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setBalance(initBalance);  // must be validated
        bankAccount.setCustomer(customer);
        bankAccount.setCreatedAt(new Date());
        bankAccount.setInterestRate(interestRate);
        return bankAccountRepository.save(bankAccount);
    }


    @Override
    public BankAccount saveAccount(BankAccount bankAccount) throws AccountNotFoundException {
        log.info("Saving account ....");
        if(bankAccount == null) throw new AccountNotFoundException("Invalid account [NULL]");
        bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public boolean deleteAccount(String accountId) throws AccountNotFoundException {
        log.info("Deleting account ....");
        BankAccount account =  getBankAccountById(accountId);
        bankAccountRepository.delete(account);
        return true;
    }

    @Override
    public BankAccount updateAccount(BankAccount bankAccount) throws AccountNotFoundException {
        log.info("Updating account ....");
        BankAccount account =  getBankAccountById(bankAccount.getId());
        return bankAccountRepository.save(account);
    }

    @Override
    public List<BankAccount> getBankAccountsList(int page, int size) {
        return null;
    }

    @Override
    public BankAccount getBankAccountById(String accountId) throws AccountNotFoundException {
        log.info("Selecting an account ....");
        return bankAccountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(null));
    }

    @Override
    public AccountType getAccountType(String accountId) throws AccountNotFoundException {
        log.info("Getting an account type....");
        BankAccount account = getBankAccountById(accountId);
        if(account instanceof  CurrentAccount) return AccountType.CURRENT_ACCOUNT;
        else if(account instanceof SavingAccount) return AccountType.SAVING_ACCOUNT;
        return null;
    }

    @Override
    public boolean applyOperation(String accountId, double amount, OperationType operationType, String description) throws OperationFailedException, AccountNotFoundException, CustomerNotFoundException, BalanceNotSufficientException {
        log.info("Applying an operation....");
        BankAccount account = getBankAccountById(accountId);

        if(amount <= 0) throw  new OperationFailedException("Operation Failed, amount <= 0 !");
        // pass operation
        if(operationType.equals(OperationType.CREDIT)){
            creditAccount(account, amount, description);
        }
        else if (operationType.equals(OperationType.DEBIT)) {
            debitAccount(account, amount, description);
        }
        return true;
    }

    @Override
    public boolean debitAccount(BankAccount account, double amount, String description) throws BalanceNotSufficientException, AccountNotFoundException, CustomerNotFoundException {
        log.info("Applying a debit....");
        double balance = account.getBalance();
        if(balance < amount) throw new BalanceNotSufficientException("Operation Failed, balance < amount !");

        // create the operation
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setOperationType(OperationType.DEBIT);
        accountOperation.setBankAccount(account);
        accountOperation.setDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(account);

        // save operation
        accountOperationService.saveOperation(accountOperation);
        // update account data

        account.setBalance(balance - amount);
        updateAccount(account);

        return true;
    }

    @Override
    public boolean creditAccount(BankAccount account, double amount, String description) throws AccountNotFoundException, CustomerNotFoundException {
        log.info("Applying a credit....");

        double balance = account.getBalance();
        // create the operation
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setOperationType(OperationType.CREDIT);
        accountOperation.setBankAccount(account);
        accountOperation.setDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(account);
        // save operation
        accountOperationService.saveOperation(accountOperation);
        // update account data

        account.setBalance(balance + amount);
        //account.getAccountOperationList().add(accountOperation);
        updateAccount(account);


        return true;
    }

    @Override
    public boolean transfer(String sourceAccount, String destinationAccount, double amountToTransfer) throws AccountNotFoundException, OperationFailedException, CustomerNotFoundException, BalanceNotSufficientException, TransferToTheSameAccountException {
        log.info("Applying a transfer....");

        final  String description = "Transfer operation from ".concat(sourceAccount).concat(" To ").concat("destination");
        if(sourceAccount.equals(destinationAccount)) throw new TransferToTheSameAccountException("Operation failed, trying to transfer from and to the same account !");
        // Making sure that the two are there (Before any operation)
        getBankAccountById(sourceAccount);
        getBankAccountById(destinationAccount);

        applyOperation(sourceAccount, amountToTransfer, OperationType.DEBIT, description);
        applyOperation(destinationAccount, amountToTransfer, OperationType.CREDIT, description);

        return false;
    }
}
