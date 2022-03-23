package com.elaamiri.patientsmanagement.services;

import com.elaamiri.patientsmanagement.Errors.EmptyObjectException;
import com.elaamiri.patientsmanagement.entities.Patient;

import java.util.List;

public interface PatientService {
    Patient insertPatient(Patient patient) throws EmptyObjectException; // TODO: handle ID
    List<Patient> getAllPatientsList();
}
