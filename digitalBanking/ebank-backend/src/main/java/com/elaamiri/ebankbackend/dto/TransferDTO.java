package com.elaamiri.ebankbackend.dto;

import lombok.Data;

@Data
public class TransferDTO {
    private String sourceAccountId;
    private String destinationAccountId;
    private double amount;
}
