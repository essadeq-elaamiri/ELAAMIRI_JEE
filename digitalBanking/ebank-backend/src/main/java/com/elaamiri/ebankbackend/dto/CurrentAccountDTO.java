package com.elaamiri.ebankbackend.dto;

import com.elaamiri.ebankbackend.entities.enumerations.AccountStatus;
import lombok.Data;
import java.util.Date;

@Data
public class CurrentAccountDTO {
    private String id;
    private Date createdAt;
    private AccountStatus status;
    private String currency;
    private CustomerDTO customerDTO;
    private double overDraft;


}
