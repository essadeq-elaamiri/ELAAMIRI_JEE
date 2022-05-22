package com.elaamiri.ebankbackend.mappers;

import com.elaamiri.ebankbackend.dto.CustomerDTO;
import com.elaamiri.ebankbackend.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Service
public class BankMapperImp implements BankMapper{

    @Override
    //map Customer to CustomerDTO
    public CustomerDTO dtoFromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }


    @Override
    public Customer customerFromDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

}
