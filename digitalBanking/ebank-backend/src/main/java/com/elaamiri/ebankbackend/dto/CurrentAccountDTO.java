package com.elaamiri.ebankbackend.dto;

import com.elaamiri.ebankbackend.entities.enumerations.AccountStatus;
import lombok.Data;
import java.util.Date;

@Data
public class CurrentAccountDTO extends BankAccountDTO {

    private double overDraft;


}
