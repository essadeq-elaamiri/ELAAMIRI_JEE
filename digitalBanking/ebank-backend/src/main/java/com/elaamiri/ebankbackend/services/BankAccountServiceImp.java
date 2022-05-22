package com.elaamiri.ebankbackend.services;

import com.elaamiri.ebankbackend.dto.*;
import com.elaamiri.ebankbackend.entities.*;
import com.elaamiri.ebankbackend.entities.enumerations.AccountType;
import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import com.elaamiri.ebankbackend.exceptions.*;
import com.elaamiri.ebankbackend.mappers.BankMapper;
import com.elaamiri.ebankbackend.repositories.BankAccountRepository;
import com.elaamiri.ebankbackend.repositories.CustomerRepository;
import com.elaamiri.ebankbackend.services.interfaces.AccountOperationService;
import com.elaamiri.ebankbackend.services.interfaces.BankAccountService;
import com.elaamiri.ebankbackend.services.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j // get log attribute (by lombok)
public class BankAccountServiceImp implements BankAccountService {
    BankAccountRepository bankAccountRepository;
    CustomerService customerService;
    AccountOperationService accountOperationService;
    BankMapper bankMapper;

    //Logger log = LoggerFactory.getLogger(this.getClass().getName()); // done by lombok

    @Override
    public CurrentAccountDTO saveCurrentAccount(double initBalance, double overDraft, String customerId) throws CustomerNotFoundException {
        log.info("Saving currentAccount ...");
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        CurrentAccountDTO bankAccountDTO =  new CurrentAccountDTO();
        bankAccountDTO.setId(UUID.randomUUID().toString());
        bankAccountDTO.setBalance(initBalance);  // must be validated
        bankAccountDTO.setCustomerDTO(customerDTO);
        bankAccountDTO.setCreatedAt(new Date());
        bankAccountDTO.setOverDraft(overDraft);
        return bankMapper.dtoFromCurrentAccount(bankAccountRepository.save(bankMapper.currentAccountFromDTO(bankAccountDTO)));

    }
    @Override
    public SavingAccountDTO saveSavingAccount(double initBalance, double interestRate, String customerId) throws CustomerNotFoundException {
        log.info("Saving savingAccount ...");
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        SavingAccountDTO bankAccountDTO =  new SavingAccountDTO();
        bankAccountDTO.setId(UUID.randomUUID().toString());
        bankAccountDTO.setBalance(initBalance);  // must be validated
        bankAccountDTO.setCustomerDTO(customerDTO);
        bankAccountDTO.setCreatedAt(new Date());
        bankAccountDTO.setInterestRate(interestRate);
        return bankMapper.dtoFromSavingAccount(bankAccountRepository.save(bankMapper.savingAccountFromDTO(bankAccountDTO)));
    }


    @Override
    public BankAccountDTO saveAccount(BankAccountDTO bankAccountDTO) throws AccountNotFoundException, CustomerNotFoundException {
        log.info("Saving account ....");
        if(bankAccountDTO == null) throw new AccountNotFoundException("Invalid account [NULL]");
        if (bankAccountDTO instanceof SavingAccountDTO){
            SavingAccountDTO savingAccountDTO =  (SavingAccountDTO) bankAccountDTO;
            return saveSavingAccount(savingAccountDTO.getBalance(), savingAccountDTO.getInterestRate(), savingAccountDTO.getCustomerDTO().getId());
        }
        else {
            CurrentAccountDTO currentAccountDTO = (CurrentAccountDTO) bankAccountDTO;
            return saveCurrentAccount(currentAccountDTO.getBalance(), currentAccountDTO.getOverDraft(), currentAccountDTO.getCustomerDTO().getId());
        }
    }

    @Override
    public boolean deleteAccount(String accountId) throws AccountNotFoundException {
        log.info("Deleting account ....");
        BankAccountDTO accountDTO =  getBankAccountById(accountId);
        bankAccountRepository.delete(bankMapper.bankAccountFromDTO(accountDTO));
        return true;
    }

    @Override
    public BankAccountDTO updateAccount(BankAccountDTO bankAccountDTO) throws AccountNotFoundException {
        log.info("Updating account ....");
        getBankAccountById(bankAccountDTO.getId());
        return bankMapper.dtoFromBankAccount(bankAccountRepository.save(bankMapper.bankAccountFromDTO(bankAccountDTO)));
    }

    @Override
    public List<BankAccountDTO> getBankAccountsList(int page, int size) {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll(PageRequest.of(page, size)).getContent();
        return  bankAccounts.stream().map(bankAccount ->bankMapper.dtoFromBankAccount(bankAccount)).collect(Collectors.toList());
    }

    @Override
    public BankAccountDTO getBankAccountById(String accountId) throws AccountNotFoundException {
        log.info("Selecting an account ....");
        return bankMapper.dtoFromBankAccount(bankAccountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(null)));
    }

    @Override
    public AccountType getAccountType(String accountId) throws AccountNotFoundException {
        log.info("Getting an account type....");
        BankAccountDTO accountDTO = getBankAccountById(accountId);
        if(accountDTO instanceof  CurrentAccountDTO) return AccountType.CURRENT_ACCOUNT;
        else if(accountDTO instanceof SavingAccountDTO) return AccountType.SAVING_ACCOUNT;
        return null;
    }

    @Override
    public boolean applyOperation(String accountId, double amount, OperationType operationType, String description) throws OperationFailedException, AccountNotFoundException, CustomerNotFoundException, BalanceNotSufficientException {
        log.info("Applying an operation....");
        BankAccountDTO account = getBankAccountById(accountId);

        if(amount <= 0) throw  new OperationFailedException("Operation Failed, amount <= 0 !");
        // pass operation
        if(operationType.equals(OperationType.CREDIT)){
            creditAccount(account, amount, description);
        }
        else if (operationType.equals(OperationType.DEBIT)) {
            debitAccount(account, amount, description);
        }
        return true;
    }

    @Override
    public boolean debitAccount(BankAccountDTO account, double amount, String description) throws BalanceNotSufficientException, AccountNotFoundException, CustomerNotFoundException {
        log.info("Applying a debit....");
        double balance = account.getBalance();
        if(balance < amount) throw new BalanceNotSufficientException("Operation Failed, balance < amount !");

        // create the operation
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        accountOperationDTO.setOperationType(OperationType.DEBIT);
        accountOperationDTO.setBankAccountDTO(account);
        accountOperationDTO.setDate(new Date());
        accountOperationDTO.setAmount(amount);
        accountOperationDTO.setDescription(description);

        // save operation
        accountOperationService.saveOperation(bankMapper.dtoFromAccountOperation(bankMapper.accountOperationFromDTO(accountOperationDTO)));
        // update account data

        account.setBalance(balance - amount);
        updateAccount(account);

        return true;
    }

    @Override
    public boolean creditAccount(BankAccountDTO account, double amount, String description) throws AccountNotFoundException, CustomerNotFoundException {
        log.info("Applying a credit....");

        double balance = account.getBalance();
        // create the operation
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        accountOperationDTO.setOperationType(OperationType.CREDIT);
        accountOperationDTO.setBankAccountDTO(account);
        accountOperationDTO.setDate(new Date());
        accountOperationDTO.setAmount(amount);
        accountOperationDTO.setDescription(description);
        // save operation
        accountOperationService.saveOperation(accountOperationDTO);
        // update account data

        account.setBalance(balance + amount);
        //account.getAccountOperationList().add(accountOperation);
        updateAccount(account);


        return true;
    }

    @Override
    public boolean transfer(String sourceAccount, String destinationAccount, double amountToTransfer) throws AccountNotFoundException, OperationFailedException, CustomerNotFoundException, BalanceNotSufficientException, TransferToTheSameAccountException {
        log.info("Applying a transfer....");

        final  String description = "Transfer operation from ".concat(sourceAccount).concat(" To ").concat("destination");
        if(sourceAccount.equals(destinationAccount)) throw new TransferToTheSameAccountException("Operation failed, trying to transfer from and to the same account !");
        // Making sure that the two are there (Before any operation)
        getBankAccountById(sourceAccount);
        getBankAccountById(destinationAccount);

        applyOperation(sourceAccount, amountToTransfer, OperationType.DEBIT, description);
        applyOperation(destinationAccount, amountToTransfer, OperationType.CREDIT, description);

        return false;
    }
}
