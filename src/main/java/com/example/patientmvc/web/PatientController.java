package com.example.patientmvc.web;


import com.example.patientmvc.Repositories.PatientRepository;
import com.example.patientmvc.entities.Patient;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping(path = "/user/index")
    public String Patient(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size,
                        @RequestParam(name = "Name", defaultValue = "") String Name)
    {
        Page<Patient> patients= patientRepository.findByNameContains(Name, PageRequest.of(page, size));

        model.addAttribute("patients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("Name", Name);
        model.addAttribute("Name2", Name);
        model.addAttribute("currentpage", page);
        return "patient";
    }

    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String Delete(Long Id, int page, String Name){
        patientRepository.deleteById(Id);
        return "redirect:/user/index?page="+ page+ "&Name="+Name;
    }

    @GetMapping(path = "/patients")
    @ResponseBody
    public List<Patient> listPatient(){
       return patientRepository.findAll();
    }

    @GetMapping(path = "/")
    public String home(){
        return "redirect:/user/index";
    }


    @GetMapping(path = "/admin/formPatients")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String formPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @PostMapping(path = "/admin/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //une fois tu cree un patient tu fait la validation
    public String Save(Model model, @Valid Patient patient1, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String Name)
    {
        if(bindingResult.hasErrors())  return "formPatients";

        System.out.println(patient1);
        patientRepository.save(patient1);
        return "redirect:/user/index?page=" +page+ "&Name="+Name;
    }

    @GetMapping(path = "/admin/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatient(Model model, Long id, int page, String Name){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("Patient introuvable");

        model.addAttribute("patient", patient);
        System.out.println(patient);
        model.addAttribute("page", page);
        model.addAttribute("Name", Name);
        return "editPatients";
    }
}
