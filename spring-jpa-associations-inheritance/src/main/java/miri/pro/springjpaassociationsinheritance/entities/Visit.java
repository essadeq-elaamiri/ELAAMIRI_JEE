package miri.pro.springjpaassociationsinheritance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
@Component
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visit { // consultation
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    private Date visitDate;
    private String consultationReport;
    @OneToOne
    private Appointment appointment;


}
