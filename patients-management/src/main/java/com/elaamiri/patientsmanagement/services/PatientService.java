package com.elaamiri.patientsmanagement.services;

import com.elaamiri.patientsmanagement.Errors.EmptyObjectException;
import com.elaamiri.patientsmanagement.Errors.PatientNotFoundException;
import com.elaamiri.patientsmanagement.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    Patient insertPatient(Patient patient) throws EmptyObjectException; // TODO: handle ID
    Page<Patient> getAllPatientsList(PageRequest pageRequest);
    Page<Patient> getPatientsListByKeyWord(String keyWord, PageRequest pageRequest);
    Patient getPatientById(String id) throws PatientNotFoundException;
    void deletePatientById(String id);

}
