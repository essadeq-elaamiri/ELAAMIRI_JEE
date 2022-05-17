package com.elaamiri.ebankbackend.services.interfaces;

import com.elaamiri.ebankbackend.entities.AccountOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountOperationService {
    // save, update, delete, getOne, getList
    AccountOperation saveOperation(AccountOperation accountOperation);
    AccountOperation updateOperation(long operationId, AccountOperation accountOperation);
    boolean deleteOperation(long operationId);

    AccountOperation getOperationById(long id);

}
