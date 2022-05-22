package com.elaamiri.ebankbackend.web;

import com.elaamiri.ebankbackend.dto.CustomerDTO;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.services.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private CustomerService customerService;
    // clients list
    @GetMapping("/customers")
    List<CustomerDTO> getCustomersList(@RequestParam(name = "page",defaultValue = "0") int page,
                                       @RequestParam(name = "size", defaultValue = "5") int size,
                                       @RequestParam(name = "keyword", defaultValue = "") String keyword){
        return customerService.getCustomersList(page, size, "");
    }
}
