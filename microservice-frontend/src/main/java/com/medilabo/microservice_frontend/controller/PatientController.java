package com.medilabo.microservice_frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medilabo.microservice_frontend.model.Note;
import com.medilabo.microservice_frontend.model.Patient;
import com.medilabo.microservice_frontend.proxy.NoteProxy;
import com.medilabo.microservice_frontend.proxy.PatientProxy;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PatientController {

    @Autowired
    private PatientProxy patientProxy;
    @Autowired
    private NoteProxy noteProxy;
    
    @GetMapping("/listpatients")
    public String listPatients(Model model) {
        List<Patient> patients = patientProxy.getPatients();
        model.addAttribute("patients", patients);
        return "ListPatients";
    }
    
    @GetMapping("/patient/view/{id}")
    public String getPatient(@PathVariable("id") String id, Model model) {
        Patient patient = patientProxy.getPatientById(id);
        model.addAttribute("patient", patient);
        List<Note> notes = noteProxy.getNotesByPatientId(id);
        model.addAttribute("notes", notes);
        return "Patient";
    }

}
