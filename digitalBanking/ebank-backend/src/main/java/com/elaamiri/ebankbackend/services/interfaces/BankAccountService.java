package com.elaamiri.ebankbackend.services.interfaces;

import com.elaamiri.ebankbackend.entities.BankAccount;
import com.elaamiri.ebankbackend.entities.CurrentAccount;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.entities.SavingAccount;
import com.elaamiri.ebankbackend.entities.enumerations.AccountType;
import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import com.elaamiri.ebankbackend.exceptions.AccountNotFoundException;
import com.elaamiri.ebankbackend.exceptions.OperationFailedException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public interface BankAccountService {
    CurrentAccount saveCurrentAccount(double initBalance, double overDraft, String customerId);
    SavingAccount saveSavingAccount(double initBalance, double interestRate, String customerId);
    BankAccount saveAccount(BankAccount bankAccount) throws AccountNotFoundException;
    boolean deleteAccount(String accountId) throws AccountNotFoundException;
    BankAccount updateAccount(BankAccount bankAccount) throws AccountNotFoundException;

    List<BankAccount> getBankAccountsList(int page, int size);

    BankAccount getBankAccountById(String accountId) throws AccountNotFoundException;

    AccountType getAccountType(String accountId) throws AccountNotFoundException;

    boolean applyOperation(String accountId, double amount, OperationType operationType, String description) throws OperationFailedException, AccountNotFoundException;

    boolean debitAccount(BankAccount account, double amount, String description) throws OperationFailedException, AccountNotFoundException;
    boolean creditAccount(BankAccount account, double amount, String description) throws AccountNotFoundException;// retrait
    boolean transfer(String sourceAccount, String destinationAccount, double amountToTransfer) throws AccountNotFoundException, OperationFailedException;

}
