package com.elaamiri.ebankbackend.services;

import com.elaamiri.ebankbackend.dto.CustomerDTO;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.exceptions.CustomerNotFoundException;
import com.elaamiri.ebankbackend.mappers.BankMapper;
import com.elaamiri.ebankbackend.repositories.CustomerRepository;
import com.elaamiri.ebankbackend.services.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j // get log attribute

public class CustomerServiceImp implements CustomerService {
    CustomerRepository customerRepository;
    BankMapper bankMapper;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTo) throws CustomerNotFoundException {
        log.info("Saving customer ....");
        if(customerDTo == null) throw new CustomerNotFoundException("Invalid customer [NULL]");
        customerDTo.setId(UUID.randomUUID().toString());
        return  bankMapper.dtoFromCustomer(customerRepository.save(bankMapper.customerFromDTO(customerDTo)));
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException {
        log.info("Updating customer ....");
        getCustomerById(customerDTO.getId());
        return bankMapper.dtoFromCustomer(customerRepository.save(bankMapper.customerFromDTO(customerDTO)));
    }

    @Override
    public boolean deleteCustomer(String customerId) throws CustomerNotFoundException {
        log.info("Deleting customer ....");
        CustomerDTO customerDTO =  getCustomerById(customerId);
        customerRepository.delete(bankMapper.customerFromDTO(customerDTO));
        return true;
    }

    @Override
    public CustomerDTO getCustomerById(String id) throws CustomerNotFoundException {
        log.info("Selecting a customer ....");
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(null));
        return bankMapper.dtoFromCustomer(customer);
    }

    @Override
    public List<CustomerDTO> getCustomersList(int page, int size, String keyword) {
        log.info("Selecting  customers ....");
        List<Customer> customersList = new ArrayList<>();
        // mapping
        if(keyword != null) customersList =  customerRepository.findCustomersByNameContains(keyword, PageRequest.of(page, size)).getContent();
        else customersList =  customerRepository.findAll( PageRequest.of(page, size)).getContent();

        return customersList.stream().map(customer -> {
            return bankMapper.dtoFromCustomer(customer);
        }).collect(Collectors.toList());
    }
}
