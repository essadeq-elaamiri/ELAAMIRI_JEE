package com.elaamiri.patientsmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    //@NotNull
    //@NotBlank
    private String id;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    private String firstName;
    @NotNull
    @NotBlank
    @Size(max = 30)
    private String lastName;
    @NotNull
    @Past // date in the past
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @NotBlank
    @Email // a valid email, we can use @pattern also
    private String email;
}
