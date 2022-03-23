package com.elaamiri.patientsmanagement.controllers;


import com.elaamiri.patientsmanagement.entities.Patient;
import com.elaamiri.patientsmanagement.repositories.PatientRepository;
import com.elaamiri.patientsmanagement.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController in cas we're creating API
@AllArgsConstructor
public class PatientController {
    /**
     * Spring MVC controller
     */
    private PatientService patientService;

    @GetMapping("/") // hey you! if a http get method call the path URL "/", call this function
    public String displayPatientsList(Model model){
        model.addAttribute("patientsList", patientService.getAllPatientsList());
        // the attribute will be accessible from the view
        return "index"; // index is the name of the view associated to the path
    }

    @GetMapping("/addNewPatient")
    public String addNewPatient(Model model){
        // thymeleaf will access this empty object for binding from data ?
        Patient patient = new Patient();
        model.addAttribute("patientObject", patient);
        return "addNewPatient";
    }

    @PostMapping("/saveNewPatient") // hey you! if a post request this url, call the folloming function
    public String saveNewPatient(@ModelAttribute("patientObject") Patient patient){
        try{
            patientService.insertPatient(patient);
        }catch (Exception exception){
            exception.getStackTrace();
        }
        return  "redirect:/";// redirect to url '/'
    }

    //editPatient
    //deletePatient
    @GetMapping("/editPatient/{id}")// show the view
    public String editPatient(@PathVariable(value = "id")String id, Model model){
        //get Patient from DB
        Patient patient = patientService.getPatientById(id);
        // send it to the view
        model.addAttribute("patientObject", patient);
        return "editPatient";
    }

    //saveEditedPatient
    @PostMapping("saveEditedPatient")
    public String saveEditedPatient(@ModelAttribute("patientObject")Patient patient){
        patientService.insertPatient(patient);
        return "redirect:/";
    }

    @DeleteMapping("/deletePatient/{id}")
    public String deletePatient(){
        return null;
    }



}
