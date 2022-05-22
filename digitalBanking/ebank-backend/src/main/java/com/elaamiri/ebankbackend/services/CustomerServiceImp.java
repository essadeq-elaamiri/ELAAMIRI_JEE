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
    public Customer saveCustomer(Customer customer) throws CustomerNotFoundException {
        log.info("Saving customer ....");
        if(customer == null) throw new CustomerNotFoundException("Invalid customer [NULL]");
        customer.setId(UUID.randomUUID().toString());
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
        log.info("Updating customer ....");
        Customer customer1 =  getCustomerById(customer.getId());
        return customerRepository.save(customer);
    }

    @Override
    public boolean deleteCustomer(String customerId) throws CustomerNotFoundException {
        log.info("Deleting customer ....");
        Customer customer =  getCustomerById(customerId);
        customerRepository.delete(customer);
        return true;
    }

    @Override
    public Customer getCustomerById(String id) throws CustomerNotFoundException {
        log.info("Selecting a customer ....");
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(null));
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
