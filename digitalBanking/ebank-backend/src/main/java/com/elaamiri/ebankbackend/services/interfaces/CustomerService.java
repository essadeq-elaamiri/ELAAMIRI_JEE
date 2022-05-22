package com.elaamiri.ebankbackend.services.interfaces;



import com.elaamiri.ebankbackend.dto.CustomerDTO;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    // save, update, delete, getOne, getList
    Customer saveCustomer(Customer customer) throws CustomerNotFoundException;
    Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
    boolean deleteCustomer(String customerId) throws CustomerNotFoundException;

    Customer getCustomerById(String id) throws CustomerNotFoundException;
    List<CustomerDTO> getCustomersList(int page, int size, String keyword);

}
