package miri.pro.springjpaassociationsinheritance.web;

import miri.pro.springjpaassociationsinheritance.entities.Patient;
import miri.pro.springjpaassociationsinheritance.repositories.PatientRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    @Autowired // injection
    PatientRepository patientRepository;

    //le path (route) pour acceder à cette fonction
    @GetMapping("/patients") // on va recevoir une liste sous fome de JSON
    public List<Patient> getPatientsList(){
        return patientRepository.findAll();
    }
}
