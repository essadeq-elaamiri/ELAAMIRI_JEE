package com.elaamiri.ebankbackend.services.interfaces;



import com.elaamiri.ebankbackend.dto.CustomerDTO;
import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    // save, update, delete, getOne, getList
    CustomerDTO saveCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException;
    CustomerDTO updateCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException;
    boolean deleteCustomer(String customerId) throws CustomerNotFoundException;

    CustomerDTO getCustomerById(String id) throws CustomerNotFoundException;
    List<CustomerDTO> getCustomersList(int page, int size, String keyword);

}
