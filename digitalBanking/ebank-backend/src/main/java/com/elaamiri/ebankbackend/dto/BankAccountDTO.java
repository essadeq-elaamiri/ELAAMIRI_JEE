package com.elaamiri.ebankbackend.dto;

import com.elaamiri.ebankbackend.entities.Customer;
import com.elaamiri.ebankbackend.entities.enumerations.AccountStatus;
import com.elaamiri.ebankbackend.entities.enumerations.AccountType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class BankAccountDTO {
    private String id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    private AccountType accountType;
    private CustomerDTO customerDTO;
}
