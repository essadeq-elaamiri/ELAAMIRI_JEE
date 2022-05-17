package com.elaamiri.ebankbackend.services;

import com.elaamiri.ebankbackend.entities.AccountOperation;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.exceptions.CustomerNotFoundException;
import com.elaamiri.ebankbackend.exceptions.OperationFailedException;
import com.elaamiri.ebankbackend.repositories.AccountOperationRepository;
import com.elaamiri.ebankbackend.services.interfaces.AccountOperationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor

public class AccountOperationServiceImp implements AccountOperationService {
    AccountOperationRepository operationRepository;
    @Override
    public AccountOperation saveOperation(AccountOperation accountOperation) {
        log.info("Saving operation ....");
        if(accountOperation == null) throw new CustomerNotFoundException("Invalid operation [NULL]");
        return operationRepository.save(accountOperation);
    }

    @Override
    public AccountOperation updateOperation(AccountOperation accountOperation) {
        log.info("Updating operation ....");
        AccountOperation operation =  getOperationById(accountOperation.getId());
        return operationRepository.save(accountOperation);
    }

    @Override
    public boolean deleteOperation(long operationId) {
        log.info("Deleting operation ....");
        AccountOperation operation =  getOperationById(operationId);
        operationRepository.delete(operation);
        return true;
    }

    @Override
    public AccountOperation getOperationById(long id) {
        log.info("Selecting an operation ....");
        return operationRepository.findById(id).orElseThrow(() -> new OperationFailedException(null));

    }
}
