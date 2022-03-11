package miri.pro.springjpaassociationsinheritance.repositories;

import miri.pro.springjpaassociationsinheritance.entities.Doctor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository  {
    void save(Doctor doctor);
}
