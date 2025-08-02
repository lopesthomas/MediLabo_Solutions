package com.medilabo.microservice_frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.medilabo.microservice_frontend.model.Patient;
import com.medilabo.microservice_frontend.proxy.PatientProxy;


@Controller
public class PatientController {

    @Autowired
    private PatientProxy patientProxy;
    
    @GetMapping("/listpatients")
    public String listPatients(Model model) {
        List<Patient> patients = patientProxy.getPatients();
        model.addAttribute("patients", patients);
        return "ListPatients";
    }
    

}
