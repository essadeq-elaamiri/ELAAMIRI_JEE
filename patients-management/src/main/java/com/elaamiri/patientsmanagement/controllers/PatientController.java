package com.elaamiri.patientsmanagement.controllers;


import com.elaamiri.patientsmanagement.Errors.PatientNotFoundException;
import com.elaamiri.patientsmanagement.entities.Patient;
import com.elaamiri.patientsmanagement.repositories.PatientRepository;
import com.elaamiri.patientsmanagement.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Lists;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
//@RestController in cas we're creating API
@AllArgsConstructor
public class PatientController {
    private final int MAXPAGES_INRANGE = 5;

    /**
     * Spring MVC controller
     */
    private PatientService patientService;

    //home
    @GetMapping("/")
    public String showHome(){
        return "home";
    }
    //home
    @GetMapping("/home")
    public String showHome2(){
        return "home";
    }


    @GetMapping("/patients") // hey you! if a http get method call the path URL "/", call this function
    // adding pagination
    public String displayPatientsList(Model model,
                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "5") int size,
                                      @RequestParam(name = "searchKeyWord", defaultValue = "")
                                              String searchKeyWord){
        Page<Patient> patientPage;
        // if there a searchKeyWord so search with it if not get all
        if(!searchKeyWord.isEmpty() && searchKeyWord != null){
            patientPage = patientService.getPatientsListByKeyWord(searchKeyWord, PageRequest.of(page, size));
            model.addAttribute("searchKeyWord", searchKeyWord);
        }
        else{
            patientPage = patientService.getAllPatientsList(PageRequest.of(page, size));
        }
        if(patientPage.isEmpty()){
            model.addAttribute("noSearchResultFoundMsg", "Sorry !No data found.");
        }
        model.addAttribute("totalElements", patientPage.getTotalElements());
        model.addAttribute("totalPages", patientPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("patientsList", patientPage.getContent());
        model.addAttribute("pages", new int[patientPage.getTotalPages()]);

        int [] pages10 =  fillPagesArray(page, patientPage.getTotalPages()); // currentPage
        // send an array of  10 elements (pages)
        model.addAttribute("pages10", pages10);
        // the attribute will be accessible from the view
        return "index"; // index is the name of the view associated to the path
    }

    private int[] fillPagesArray(int currentPage, int numberOfPages){
        /*
        returns an array, filled with the numbers of pages.
        in every call, it returns a list of numbers that contain the currentPage
        ex : currentPage is 9 so it is included in the group 2 because the groupe 1 contains (0-4), g2 (5-10)
        so the array starts from 5 and ends at 10

         */
        // page10Range = (int) currentPage / 10, so I can know with what to fill my array
        int page10Range = (int) (currentPage / MAXPAGES_INRANGE); // find the group of the currentPage
        //int [] page10= new int[MAXPAGES_INRANGE];
        // init array to be in the size of 5 if the number of pages greater than 5, if not keep it as the number of page
        //int page10Size = numberOfPages / MAXPAGES_INRANGE >= 1 ? MAXPAGES_INRANGE : numberOfPages;
        int page10Size = (numberOfPages - (MAXPAGES_INRANGE * page10Range)) / MAXPAGES_INRANGE >= 1 ? MAXPAGES_INRANGE : (numberOfPages - (MAXPAGES_INRANGE * page10Range)) % MAXPAGES_INRANGE;
        int [] page10= new int[page10Size];
        for (int i= 0; i < page10.length; i++){
            if(((MAXPAGES_INRANGE * page10Range ) + i) >= numberOfPages ){
                break;
            }
            page10[i] = (MAXPAGES_INRANGE * page10Range ) + i;

            //if(currentPage >= numberOfPages) page10[i]=i;
        }
        return page10;
    }

    @GetMapping("/addNewPatient")
    public String addNewPatient(Model model){
        // thymeleaf will access this empty object for binding from data ?
        Patient patient = new Patient();
        model.addAttribute("patientObject", patient);
        return "addNewPatient";
    }

    /*@PostMapping("/saveNewPatient") // hey you! if a post request this url, call the folloming function
    public String
    saveNewPatient(@ModelAttribute("patientObject") @Valid Patient patient,
                                 BindingResult bindingResult,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "5") int size,
                                 @RequestParam(name = "searchKeyWord", defaultValue = "")
                                 String searchKeyWord
                                ){
        // tests
        if(bindingResult.hasErrors()){
            *//*for (ObjectError err :bindingResult.getAllErrors()){
                System.out.println(err);

            }*//*
            return "addNewPatient"; // redirect to form to show errors
        }
        else{
            patientService.insertPatient(patient);
            // send a success message
            //model.addAttribute("patientSuccessInsertionMsg", "Patient inserted successfully");
            return "redirect:/?page="+page+"&size="+size+"&searchKeyWord="+searchKeyWord;
        }


    }*/

    @PostMapping("/saveNewPatient") // hey you! if a post request this url, call the folloming function
    public String
    saveNewPatient(@ModelAttribute("patientObject") @Valid Patient patient,
                   BindingResult bindingResult,
                   @RequestParam(name = "page", defaultValue = "0") int page,
                   @RequestParam(name = "size", defaultValue = "5") int size,
                   @RequestParam(name = "searchKeyWord", defaultValue = "")
                           String searchKeyWord
    ){
        return savePatient(patient, bindingResult, page, size, searchKeyWord, "addNewPatient");
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
        if(patient == null) throw new PatientNotFoundException("Patient with id: "+id+", not found !");
        // send it to the view
        model.addAttribute("patientObject", patient);
        model.addAttribute("searchKeyWord", searchKeyWord);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);

        return "editPatient";
    }

    //saveEditedPatient
    @PostMapping("/saveEditedPatient")
    public String saveEditedPatient(@ModelAttribute("patientObject") @Valid Patient patient,
                                    BindingResult bindingResult,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "5") int size,
                                    @RequestParam(name = "searchKeyWord", defaultValue = "")
                                                String searchKeyWord){

        return savePatient(patient, bindingResult, page, size, searchKeyWord, "editPatient");
    }

    private String savePatient(Patient patient,BindingResult bindingResult,int page,int size,String searchKeyWord, String ifValidationErrorRedirectTo) {
        // tests
        if (bindingResult.hasErrors()) {
            /*for (ObjectError err :bindingResult.getAllErrors()){
                System.out.println(err);

            }*/
            return ifValidationErrorRedirectTo; // redirect to form to show errors
        } else {
            patientService.insertPatient(patient);
            // send a success message
            //model.addAttribute("patientSuccessInsertionMsg", "Patient inserted successfully");
            return "redirect:/patients?page=" + page + "&size=" + size + "&searchKeyWord=" + searchKeyWord;
        }
    }


    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable(value = "id")String id,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "5") int size,
                                @RequestParam(name = "searchKeyWord", defaultValue = "") String searchKeyWord){
        //System.out.println(currentPage);
        patientService.deletePatientById(id);
        // TODO: delete
        //System.out.println("from delete: "+ searchKeyWord);
        //System.out.println("from delete null?: "+ (searchKeyWord == null));
        return "redirect:/patients?page="+page+"&size="+size+"&searchKeyWord="+(searchKeyWord == null ? "" : searchKeyWord);
    }

    // Rest API
    @GetMapping("/apiv1/patients") // by default, here the ServletDispatcher will understand there it will not return a view
    @ResponseBody // All  RestController's methods are @ResponseBody
    public List<Patient> getPatientsList(){
        return  patientService.getAllPatientsList(PageRequest.of(0, 50)).getContent();
    }



}
