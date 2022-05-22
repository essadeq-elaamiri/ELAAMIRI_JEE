package com.elaamiri.ebankbackend.mappers;

import com.elaamiri.ebankbackend.dto.CustomerDTO;
import com.elaamiri.ebankbackend.entities.Customer;
import org.springframework.stereotype.Component;

@Service
public interface BankMapper {
    //map Customer to CustomerDTO
    CustomerDTO dtoFromCustomer(Customer customer);

    Customer customerFromDTO(CustomerDTO customerDTO);
}
