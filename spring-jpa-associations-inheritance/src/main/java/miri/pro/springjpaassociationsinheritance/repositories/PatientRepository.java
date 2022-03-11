package miri.pro.springjpaassociationsinheritance.repositories;

import miri.pro.springjpaassociationsinheritance.entities.Appointment;
import miri.pro.springjpaassociationsinheritance.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;


public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findPatientByName(String name);
}
