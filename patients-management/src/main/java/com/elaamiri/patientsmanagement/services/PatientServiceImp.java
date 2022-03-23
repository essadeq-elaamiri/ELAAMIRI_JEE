package com.elaamiri.patientsmanagement.services;

import com.elaamiri.patientsmanagement.Errors.EmptyObjectException;
import com.elaamiri.patientsmanagement.entities.Patient;
import com.elaamiri.patientsmanagement.repositories.PatientRepository;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class PatientServiceImp implements PatientService{
    // injection by constructor
    private PatientRepository patientRepository;

    @Override
    public Patient insertPatient(Patient patient) throws EmptyObjectException {
        if(patient == null){
            throw new EmptyObjectException("Patient object is null!");
        }
        patient.setId(UUID.randomUUID().toString());
        // TODO: validations , exceptions ...
        return null;
    }

    @Override
    public List<Patient> getAllPatientsList() {
        // TODO: pagination
        return patientRepository.findAll();
    }
}
