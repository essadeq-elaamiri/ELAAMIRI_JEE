package com.elaamiri.patientsmanagement.services;

import com.elaamiri.patientsmanagement.Errors.EmptyObjectException;
import com.elaamiri.patientsmanagement.Errors.PatientNotFoundException;
import com.elaamiri.patientsmanagement.entities.Patient;
import com.elaamiri.patientsmanagement.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service // important : solved the injection error
@AllArgsConstructor
public class PatientServiceImp implements PatientService{
    // injection by constructor
    private PatientRepository patientRepository;

    @Override
    public Patient insertPatient(Patient patient) {
        // TODO: validations , exceptions ...
        if(patient == null){
            throw new EmptyObjectException("Patient object is null!");
        }
        if(patient.getId() == null){
            System.out.println("Empty id in: " +patient.getId());
            patient.setId(UUID.randomUUID().toString());
        }
        System.out.println("Empty id out: " +patient.getId().isEmpty());
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatientsList() {
        // TODO : pagination
        return patientRepository.findAll(); // video: 1805
    }

    @Override
    public Patient getPatientById(String id) throws PatientNotFoundException {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(optionalPatient.isPresent()) return optionalPatient.get();
        else throw new PatientNotFoundException("No patient with this id:" +id + " found!");
    }

    @Override
    public void deletePatientById(String id) {
         patientRepository.deleteById(id);
    }
}
