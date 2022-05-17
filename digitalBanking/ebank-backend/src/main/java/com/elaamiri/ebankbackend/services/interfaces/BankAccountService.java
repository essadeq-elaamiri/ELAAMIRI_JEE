package com.elaamiri.ebankbackend.services.interfaces;

import com.elaamiri.ebankbackend.entities.BankAccount;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.entities.enumerations.AccountType;
import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public interface BankAccountService {
    BankAccount saveAccount(double initBalance, AccountType accountType, String customerId);
    BankAccount saveAccount(BankAccount bankAccount);
    boolean deleteAccount(String accountId);
    BankAccount updateAccount(String accountId, BankAccount bankAccount);

    List<BankAccount> getBankAccountsList(int page, int size);

    BankAccount getBankAccountById(String accountId);

    AccountType getAccountType(String accountId);

    boolean applyOperation(String accountId, double amount, OperationType operationType, String description);

    boolean debitAccount(String accountId, double amount, String description);
    boolean creditAccount(String accountId, double amount, String description);// retrait
    boolean transfer(String sourceAccount, String destinationAccount, boolean amountToTransfer);

}
