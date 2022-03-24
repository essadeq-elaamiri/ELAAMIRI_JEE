package com.elaamiri.patientsmanagement.services;

import com.elaamiri.patientsmanagement.Errors.EmptyObjectException;
import com.elaamiri.patientsmanagement.Errors.PatientNotFoundException;
import com.elaamiri.patientsmanagement.entities.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    Patient insertPatient(Patient patient) throws EmptyObjectException; // TODO: handle ID
    List<Patient> getAllPatientsList();
    Patient getPatientById(String id) throws PatientNotFoundException;
    void deletePatientById(String id);

}
