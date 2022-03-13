package miri.pro.springjpaassociationsinheritance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Person{

    @Temporal(TemporalType.DATE)
    private Date birth;
    @OneToMany(mappedBy = "patient")
    private Collection<Appointment> appointmentCollection;
}
