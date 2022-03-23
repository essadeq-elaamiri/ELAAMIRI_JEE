package com.elaamiri.patientsmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "PATIENT")
public class Patient {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "PATIENT_ID")
    private String id;
    private String firstName;
    private String LastName;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String email;
}
