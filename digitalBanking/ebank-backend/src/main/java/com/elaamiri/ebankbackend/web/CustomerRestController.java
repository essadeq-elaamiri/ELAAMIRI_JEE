package com.elaamiri.ebankbackend.web;

import com.elaamiri.ebankbackend.dto.CustomerDTO;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.exceptions.CustomerNotFoundException;
import com.elaamiri.ebankbackend.mappers.BankMapper;
import com.elaamiri.ebankbackend.services.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")

public class CustomerRestController {
    private CustomerService customerService;
    // clients list
    @GetMapping("/customers")
    public List<CustomerDTO> getCustomersList(@RequestParam(name = "page",defaultValue = "0") int page,
                                       @RequestParam(name = "size", defaultValue = "5") int size,
                                       @RequestParam(name = "keyword", defaultValue = "") String keyword){
        return customerService.getCustomersList(page, size, "");
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable String id) throws CustomerNotFoundException {

        return customerService.getCustomerById(id);
    }

    // saving a customer
    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException {
        log.warn(customerDTO.getName());
        return customerService.saveCustomer(customerDTO);
    }
    // updating a record
    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException {
        customerDTO.setId(id);
        // TODO: correct error return the same customer without editings
        return customerService.saveCustomer(customerDTO);
    }

    // delete a customer
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable String id) throws CustomerNotFoundException {
        customerService.deleteCustomer(id);
    }

}
