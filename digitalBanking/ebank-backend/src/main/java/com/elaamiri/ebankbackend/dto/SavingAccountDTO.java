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

public class SavingAccountDTO  extends BankAccountDTO{


    private double interestRate; // taux d'interêt



}
