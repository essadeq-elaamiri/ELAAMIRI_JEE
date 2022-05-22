package com.elaamiri.ebankbackend.web;

import com.elaamiri.ebankbackend.dto.CurrentAccountDTO;
import com.elaamiri.ebankbackend.dto.currentAccountDTO;
import com.elaamiri.ebankbackend.services.interfaces.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestController {
    BankAccountService bankAccountService;

    // get all accounts
    @GetMapping("/accounts")
    public List<CurrentAccountDTO> getAllCurrentAccounts(@RequestParam int page, @RequestParam int size){
        return bankAccountService.get
    }

}
