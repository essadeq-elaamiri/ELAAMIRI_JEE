package com.elaamiri.ebankbackend.web;

import com.elaamiri.ebankbackend.dto.BankAccountDTO;
import com.elaamiri.ebankbackend.dto.CurrentAccountDTO;
import com.elaamiri.ebankbackend.exceptions.AccountNotFoundException;
import com.elaamiri.ebankbackend.exceptions.CustomerNotFoundException;
import com.elaamiri.ebankbackend.services.interfaces.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestController {
    BankAccountService bankAccountService;

    // get all accounts
    @GetMapping("/accounts")
    public List<BankAccountDTO> getAllCurrentAccounts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return bankAccountService.getBankAccountsList(page, size);
    }

    @GetMapping("/accounts/{id}")
    public BankAccountDTO getBankAccount(@PathVariable String id) throws AccountNotFoundException {
        return bankAccountService.getBankAccountById(id);
    }

    @PostMapping("/accounts")
    public BankAccountDTO saveBankAccount(@RequestBody BankAccountDTO bankAccountDTO) throws AccountNotFoundException, CustomerNotFoundException {
        return  bankAccountService.saveAccount(bankAccountDTO);
    }

}
