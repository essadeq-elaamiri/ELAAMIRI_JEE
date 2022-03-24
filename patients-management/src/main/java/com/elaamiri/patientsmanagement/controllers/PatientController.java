package com.elaamiri.patientsmanagement.controllers;


import com.elaamiri.patientsmanagement.entities.Patient;
import com.elaamiri.patientsmanagement.repositories.PatientRepository;
import com.elaamiri.patientsmanagement.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController in cas we're creating API
@AllArgsConstructor
public class PatientController {
    /**
     * Spring MVC controller
     */
    private PatientService patientService;

    @GetMapping("/") // hey you! if a http get method call the path URL "/", call this function
    // adding pagination
    public String displayPatientsList(Model model,
                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "5") int size,
                                      @RequestParam(name = "searchKeyWord", defaultValue = "") String searchKeyWord){
        Page<Patient> patientPage;
        // if there a searchKeyWord so search with it if not get all
        if(!searchKeyWord.isEmpty() && searchKeyWord != null){
            patientPage = patientService.getPatientsListByKeyWord(searchKeyWord, PageRequest.of(page, size));
            if (patientPage.isEmpty()){
                model.addAttribute("noSearchResultFoundMsg", "Sorry !No patient found with the given search ("+ searchKeyWord +").");
                patientPage = patientService.getAllPatientsList(PageRequest.of(page, size));
            }
            model.addAttribute("searchKeyWord", searchKeyWord);
        }
        else{
            patientPage = patientService.getAllPatientsList(PageRequest.of(page, size));
        }
        model.addAttribute("totalElements", patientPage.getTotalElements());
        model.addAttribute("totalPages", patientPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("patientsList", patientPage.getContent());
        model.addAttribute("pages", new int[patientPage.getTotalPages()]);

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
    public String saveNewPatient(@ModelAttribute("patientObject") Patient patient,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "5") int size,
                                 @RequestParam(name = "searchKeyWord", defaultValue = "") String searchKeyWord){
        patientService.insertPatient(patient);
        return "redirect:/?page="+page+"&size="+size+"&searchKeyWord="+searchKeyWord;
    }

    //editPatient
    //deletePatient
    @GetMapping("/editPatient/{id}")// show the view
    public String editPatient(@PathVariable(value = "id")String id,
                              Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "5") int size,
                              @RequestParam(name = "searchKeyWord", defaultValue = "") String searchKeyWord){
        //get Patient from DB
        Patient patient = patientService.getPatientById(id);
        // send it to the view
        model.addAttribute("patientObject", patient);
        model.addAttribute("searchKeyWord", searchKeyWord);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);

        return "editPatient";
    }
/*
    //saveEditedPatient
    @PostMapping("saveEditedPatient")
    public String saveEditedPatient(@ModelAttribute("patientObject")Patient patient){
        patientService.insertPatient(patient);
        return "redirect:/";
    }
*/
    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable(value = "id")String id,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "5") int size,
                                @RequestParam(name = "searchKeyWord", defaultValue = "") String searchKeyWord){
        //System.out.println(currentPage);
        patientService.deletePatientById(id);
        return "redirect:/?page="+page+"&size="+size+"&searchKeyWord="+searchKeyWord;
    }

    // Rest API
    @GetMapping("/apiv1/patients") // by default, here the ServletDispatcher will understand there it will not return a view
    @ResponseBody // All  RestController's methods are @ResponseBody
    public List<Patient> getPatientsList(){
        return  patientService.getAllPatientsList(PageRequest.of(0, 50)).getContent();
    }



}
