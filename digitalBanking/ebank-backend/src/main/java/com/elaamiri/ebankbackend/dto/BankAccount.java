package com.elaamiri.ebankbackend.dto;

import com.elaamiri.ebankbackend.entities.AccountOperation;
import com.elaamiri.ebankbackend.entities.enumerations.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data

public class BankAccount {

    private String id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;

    @ManyToOne()
    private CustomerDTO customer;

    @OneToMany(mappedBy = "bankAccount")
    private List<AccountOperation> accountOperationList;

}
