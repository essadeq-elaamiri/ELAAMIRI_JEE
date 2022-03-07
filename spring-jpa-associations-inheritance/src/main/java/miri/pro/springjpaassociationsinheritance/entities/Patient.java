package miri.pro.springjpaassociationsinheritance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date birth;
    @OneToMany(mappedBy = "patient")
    private Collection<Appointment> appointmentCollection;
}
