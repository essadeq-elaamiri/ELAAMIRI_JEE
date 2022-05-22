package com.elaamiri.ebankbackend.services.interfaces;

import com.elaamiri.ebankbackend.dto.AccountOperationDTO;
import com.elaamiri.ebankbackend.entities.AccountOperation;
import com.elaamiri.ebankbackend.exceptions.CustomerNotFoundException;
import com.elaamiri.ebankbackend.exceptions.OperationFailedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountOperationService {
    // save, update, delete, getOne, getList

    AccountOperationDTO saveOperation(AccountOperationDTO accountOperation) throws CustomerNotFoundException;


    AccountOperationDTO updateOperation(AccountOperationDTO accountOperation) throws OperationFailedException;

    boolean deleteOperation(long operationId) throws OperationFailedException;

    AccountOperationDTO getOperationById(long id) throws OperationFailedException;

}
