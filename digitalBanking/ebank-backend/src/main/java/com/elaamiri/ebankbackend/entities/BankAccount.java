package com.elaamiri.ebankbackend.entities;

import com.elaamiri.ebankbackend.entities.enumerations.AccountStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class BankAccount {
    @Id
    private String id;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;



}
