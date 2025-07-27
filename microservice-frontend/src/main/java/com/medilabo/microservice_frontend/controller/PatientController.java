package com.medilabo.microservice_frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.medilabo.microservice_frontend.model.Patient;


@Controller
public class PatientController {

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/listpatients")
    public String listPatients(Model model) {
        Patient[] patients = restTemplate.getForObject("http://localhost:8081/patients/list", Patient[].class);
        model.addAttribute("patients", patients);
        return "ListPatients";
    }
    

}
