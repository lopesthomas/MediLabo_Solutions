package com.medilabo.microservice_frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.medilabo.microservice_frontend.model.Note;
import com.medilabo.microservice_frontend.model.Patient;
import com.medilabo.microservice_frontend.proxy.NoteProxy;
import com.medilabo.microservice_frontend.proxy.PatientProxy;




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

    @GetMapping("/patient/add")
    public String addPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "AddPatient";
    }

    @PostMapping("/patient/add")
    public String addPatient(@ModelAttribute("patient") Patient patient) {
        patientProxy.addPatient(patient);
        return "redirect:/listpatients";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable("id") String id) {
        patientProxy.deletePatient(id);
        return "redirect:/listpatients";
    }

}
