package com.elaamiri.ebankbackend.services.interfaces;

import com.elaamiri.ebankbackend.dto.AccountHistoryDTO;
import com.elaamiri.ebankbackend.dto.AccountOperationDTO;
import com.elaamiri.ebankbackend.dto.BankAccountDTO;
import com.elaamiri.ebankbackend.entities.AccountOperation;
import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import com.elaamiri.ebankbackend.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountOperationService {
    // save, update, delete, getOne, getList

    AccountOperationDTO saveOperation(AccountOperationDTO accountOperation) throws CustomerNotFoundException;


    AccountOperationDTO updateOperation(AccountOperationDTO accountOperation) throws OperationFailedException;

    boolean deleteOperation(long operationId) throws OperationFailedException;

    AccountOperationDTO getOperationById(long id) throws OperationFailedException;

    List<AccountOperationDTO> getAllOperations(int page, int size) throws AccountNotFoundException;

    List<AccountOperationDTO> getAccountOperations(String accountId, int page, int size) throws AccountNotFoundException;

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws AccountNotFoundException;

    boolean applyOperation(String accountId, double amount, OperationType operationType, String description) throws OperationFailedException, AccountNotFoundException, CustomerNotFoundException, BalanceNotSufficientException;

    boolean debitAccount(BankAccountDTO account, double amount, String description) throws BalanceNotSufficientException, AccountNotFoundException, CustomerNotFoundException;

    boolean creditAccount(BankAccountDTO account, double amount, String description) throws AccountNotFoundException, CustomerNotFoundException;

    boolean transfer(String sourceAccount, String destinationAccount, double amountToTransfer) throws AccountNotFoundException, OperationFailedException, CustomerNotFoundException, BalanceNotSufficientException, TransferToTheSameAccountException;
}
