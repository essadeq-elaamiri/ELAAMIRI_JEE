package com.elaamiri.ebankbackend.web;

import com.elaamiri.ebankbackend.dto.*;
import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import com.elaamiri.ebankbackend.exceptions.*;
import com.elaamiri.ebankbackend.services.interfaces.AccountOperationService;
import com.elaamiri.ebankbackend.services.interfaces.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class BankAccountRestController {
    BankAccountService bankAccountService;
    AccountOperationService accountOperationService;

    // get all accounts
    @GetMapping("/accounts")
    public List<BankAccountDTO> getAllCurrentAccounts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return bankAccountService.getBankAccountsList(page, size);
    }

    @GetMapping("/accounts/{id}")
    public BankAccountDTO getBankAccount(@PathVariable String id) throws AccountNotFoundException {
        return bankAccountService.getBankAccountById(id);
    }

    @GetMapping("/accounts/{id}/operations")
    public List<AccountOperationDTO> getBankAccountOperations(@PathVariable String id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) throws AccountNotFoundException {
        return accountOperationService.getAccountOperations(id, page, size);
    }

    @GetMapping("/accounts/{id}/history")
    public AccountHistoryDTO getBankAccountHistory(@PathVariable String id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) throws AccountNotFoundException {
        return accountOperationService.getAccountHistory(id, page, size);
    }

    @PostMapping("/accounts")
    public BankAccountDTO saveBankAccount(@RequestBody BankAccountDTO bankAccountDTO) throws AccountNotFoundException, CustomerNotFoundException {
        return  bankAccountService.saveAccount(bankAccountDTO);
    }

    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws AccountNotFoundException, BalanceNotSufficientException, OperationFailedException, CustomerNotFoundException {
        accountOperationService.applyOperation(debitDTO.getAccountId(), debitDTO.getAmount(), OperationType.DEBIT, debitDTO.getDescription());
        return debitDTO;
    }

    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws AccountNotFoundException, BalanceNotSufficientException, OperationFailedException, CustomerNotFoundException {
        System.out.println(creditDTO);
        accountOperationService.applyOperation(creditDTO.getAccountId(), creditDTO.getAmount(), OperationType.CREDIT, creditDTO.getDescription());
        return creditDTO;
    }
    @PostMapping("/accounts/transfer")
    public TransferDTO transfer(@RequestBody TransferDTO transferDTO) throws TransferToTheSameAccountException, AccountNotFoundException, BalanceNotSufficientException, OperationFailedException, CustomerNotFoundException {
        System.out.println(transferDTO);
        accountOperationService.transfer(transferDTO.getSourceAccountId(), transferDTO.getDestinationAccountId(), transferDTO.getAmount());
        return transferDTO;
    }

}
