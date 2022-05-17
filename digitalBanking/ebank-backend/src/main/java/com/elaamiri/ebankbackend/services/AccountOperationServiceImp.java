package com.elaamiri.ebankbackend.services;

import com.elaamiri.ebankbackend.entities.AccountOperation;
import com.elaamiri.ebankbackend.services.interfaces.AccountOperationService;
import org.springframework.stereotype.Service;

@Service
public class AccountOperationServiceImp implements AccountOperationService {
    @Override
    public AccountOperation saveOperation(AccountOperation accountOperation) {
        return null;
    }

    @Override
    public AccountOperation updateOperation(long operationId, AccountOperation accountOperation) {
        return null;
    }

    @Override
    public boolean deleteOperation(long operationId) {
        return false;
    }

    @Override
    public AccountOperation getOperationById(long id) {
        return null;
    }
}
