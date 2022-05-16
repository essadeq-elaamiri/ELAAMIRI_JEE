package com.elaamiri.ebankbackend.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Customer {
    @Id
    private String id;
    private String name;
    private String email;

    
}
