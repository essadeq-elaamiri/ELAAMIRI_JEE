package miri.pro.springjpaassociationsinheritance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String speciality;

    @OneToMany(mappedBy = "doctor")
    private Collection<Appointment> appointmentCollection;
}
