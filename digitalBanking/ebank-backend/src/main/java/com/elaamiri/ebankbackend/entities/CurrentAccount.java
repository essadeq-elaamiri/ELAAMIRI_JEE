package com.elaamiri.ebankbackend.entities;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class CurrentAccount extends BankAccount{
    private double overDraft;
}
