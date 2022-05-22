package com.elaamiri.ebankbackend.dto;

import com.elaamiri.ebankbackend.entities.BankAccount;
import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
public class AccountOperationDTO {

    private long id;
    private Date date;
    private double amount;
    private OperationType operationType;

    private BankAccountDTO bankAccountDTO;

    private String description;
}
