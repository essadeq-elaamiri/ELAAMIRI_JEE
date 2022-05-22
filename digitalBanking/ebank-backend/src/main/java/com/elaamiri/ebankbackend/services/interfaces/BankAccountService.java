package com.elaamiri.ebankbackend.services.interfaces;

import com.elaamiri.ebankbackend.dto.BankAccountDTO;
import com.elaamiri.ebankbackend.dto.CurrentAccountDTO;
import com.elaamiri.ebankbackend.dto.SavingAccountDTO;
import com.elaamiri.ebankbackend.entities.BankAccount;
import com.elaamiri.ebankbackend.entities.CurrentAccount;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.entities.SavingAccount;
import com.elaamiri.ebankbackend.entities.enumerations.AccountType;
import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import com.elaamiri.ebankbackend.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public interface BankAccountService {
    CurrentAccountDTO saveCurrentAccount(double initBalance, double overDraft, String customerId) throws CustomerNotFoundException;
    SavingAccountDTO saveSavingAccount(double initBalance, double interestRate, String customerId) throws CustomerNotFoundException;
    BankAccountDTO saveAccount(BankAccountDTO bankAccount) throws AccountNotFoundException, CustomerNotFoundException;
    boolean deleteAccount(String accountId) throws AccountNotFoundException;
    BankAccountDTO updateAccount(BankAccountDTO bankAccount) throws AccountNotFoundException;

    List<BankAccountDTO> getBankAccountsList(int page, int size);

    BankAccountDTO getBankAccountById(String accountId) throws AccountNotFoundException;

    AccountType getAccountType(String accountId) throws AccountNotFoundException;

    boolean applyOperation(String accountId, double amount, OperationType operationType, String description) throws OperationFailedException, AccountNotFoundException, CustomerNotFoundException, BalanceNotSufficientException;

    boolean debitAccount(BankAccountDTO account, double amount, String description) throws OperationFailedException, AccountNotFoundException, BalanceNotSufficientException, CustomerNotFoundException;
    boolean creditAccount(BankAccountDTO account, double amount, String description) throws AccountNotFoundException, CustomerNotFoundException;// retrait
    boolean transfer(String sourceAccount, String destinationAccount, double amountToTransfer) throws AccountNotFoundException, OperationFailedException, CustomerNotFoundException, BalanceNotSufficientException, TransferToTheSameAccountException;

}
