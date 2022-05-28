package com.elaamiri.ebankbackend.web;

import com.elaamiri.ebankbackend.dto.AccountHistoryDTO;
import com.elaamiri.ebankbackend.dto.AccountOperationDTO;
import com.elaamiri.ebankbackend.dto.BankAccountDTO;
import com.elaamiri.ebankbackend.dto.CurrentAccountDTO;
import com.elaamiri.ebankbackend.exceptions.AccountNotFoundException;
import com.elaamiri.ebankbackend.exceptions.CustomerNotFoundException;
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

}
