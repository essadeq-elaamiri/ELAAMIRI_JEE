package com.elaamiri.ebankbackend.entities;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class SavingAccount extends BankAccount {
    private double interestRate;
}
