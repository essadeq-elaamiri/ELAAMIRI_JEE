package com.elaamiri.ebankbackend.entities;

import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //strategy = GenerationType.AUTO means, pick the auto (default) strategy by the framework
    private long id;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date date;
    private double amount;
    private OperationType operationType;

    @ManyToOne()
    private BankAccount bankAccount;


}
