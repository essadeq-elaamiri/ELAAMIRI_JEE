package com.elaamiri.ebankbackend.services;

import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.services.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    @Override
    public Customer saveCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer updateCustomer(String customerId) {
        return null;
    }

    @Override
    public boolean deleteCustomer(String customerId) {
        return false;
    }

    @Override
    public Customer getCustomerById(String id) {
        return null;
    }

    @Override
    public List<Customer> getCustomersList(int page, int size, String keyword) {
        return null;
    }
}
