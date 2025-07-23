package com.medilabo.microservice_patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.microservice_patient.model.Patient;
import com.medilabo.microservice_patient.service.PatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public List<Patient> listPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping("/update")
    public Patient updatePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }
    

}
