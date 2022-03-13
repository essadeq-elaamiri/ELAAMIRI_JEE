package miri.pro.springjpaassociationsinheritance.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Doctor extends Person{

    private String speciality;

    @OneToMany(mappedBy = "doctor")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Appointment> appointmentCollection;
}
