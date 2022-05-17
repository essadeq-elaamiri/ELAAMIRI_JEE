package com.elaamiri.ebankbackend.services.interfaces;



import com.elaamiri.ebankbackend.entities.Customer;

import java.util.List;

public interface CustomerService {
    // save, update, delete, getOne, getList
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    boolean deleteCustomer(String customerId);

    Customer getCustomerById(String id);
    List<Customer> getCustomersList(int page, int size, String keyword);

}
