package com.elaamiri.ebankbackend.web;

import com.elaamiri.ebankbackend.dto.AccountOperationDTO;
import com.elaamiri.ebankbackend.entities.AccountOperation;
import com.elaamiri.ebankbackend.services.interfaces.AccountOperationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountOperationRestController {
    AccountOperationService accountOperationService;

    @GetMapping("/operations")
    public List<AccountOperationDTO> getOperationsList(){
        return  null;
    }
}
