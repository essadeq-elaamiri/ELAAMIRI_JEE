package com.elaamiri.ebankbackend.services.interfaces;

import com.elaamiri.ebankbackend.entities.AccountOperation;
import com.elaamiri.ebankbackend.exceptions.CustomerNotFoundException;
import com.elaamiri.ebankbackend.exceptions.OperationFailedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountOperationService {
    // save, update, delete, getOne, getList
    AccountOperation saveOperation(AccountOperation accountOperation) throws CustomerNotFoundException;
    AccountOperation updateOperation(AccountOperation accountOperation) throws OperationFailedException;
    boolean deleteOperation(long operationId) throws OperationFailedException;

    AccountOperation getOperationById(long id) throws OperationFailedException;

}
