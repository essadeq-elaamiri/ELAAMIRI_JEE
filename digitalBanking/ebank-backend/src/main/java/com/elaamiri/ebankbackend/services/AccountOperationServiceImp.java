package com.elaamiri.ebankbackend.services;

import com.elaamiri.ebankbackend.dto.AccountOperationDTO;
import com.elaamiri.ebankbackend.entities.AccountOperation;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.exceptions.CustomerNotFoundException;
import com.elaamiri.ebankbackend.exceptions.OperationFailedException;
import com.elaamiri.ebankbackend.mappers.BankMapper;
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
    BankMapper bankMapper;
    @Override
    public AccountOperationDTO saveOperation(AccountOperationDTO accountOperation) throws CustomerNotFoundException {
        log.info("Saving operation ....");
        if(accountOperation == null) throw new CustomerNotFoundException("Invalid operation [NULL]");
        return bankMapper.dtoFromAccountOperation(operationRepository.save(bankMapper.accountOperationFromDTO(accountOperation)));
    }

    @Override
    public AccountOperationDTO updateOperation(AccountOperationDTO accountOperation) throws OperationFailedException {
        log.info("Updating operation ....");
        getOperationById(accountOperation.getId());
        return bankMapper.dtoFromAccountOperation(operationRepository.save(bankMapper.accountOperationFromDTO(accountOperation)));
    }

    @Override
    public boolean deleteOperation(long operationId) throws OperationFailedException {
        log.info("Deleting operation ....");
        AccountOperationDTO operationDTO =  getOperationById(operationId);
        operationRepository.delete(bankMapper.accountOperationFromDTO(operationDTO));
        return true;
    }

    @Override
    public AccountOperationDTO getOperationById(long id) throws OperationFailedException {
        log.info("Selecting an operation ....");
        return bankMapper.dtoFromAccountOperation(operationRepository.findById(id).orElseThrow(() -> new OperationFailedException(null)));

    }
}
