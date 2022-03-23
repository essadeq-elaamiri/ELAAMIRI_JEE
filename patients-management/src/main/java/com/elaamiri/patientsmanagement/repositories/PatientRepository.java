package com.elaamiri.patientsmanagement.repositories;

import com.elaamiri.patientsmanagement.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

}
