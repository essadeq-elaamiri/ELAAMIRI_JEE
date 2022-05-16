package com.elaamiri.ebankbackend.entities;

import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date date;
    private double amount;
    private OperationType operationType;



}
