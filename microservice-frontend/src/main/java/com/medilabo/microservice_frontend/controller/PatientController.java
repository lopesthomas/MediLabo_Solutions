package com.medilabo.microservice_frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.medilabo.microservice_frontend.dto.RapportDTO;
import com.medilabo.microservice_frontend.model.Note;
import com.medilabo.microservice_frontend.model.Patient;
import com.medilabo.microservice_frontend.proxy.NoteProxy;
import com.medilabo.microservice_frontend.proxy.PatientProxy;
import com.medilabo.microservice_frontend.proxy.RapportProxy;

import jakarta.validation.Valid;




@Controller
public class PatientController {

    @Autowired
    private PatientProxy patientProxy;
    @Autowired
    private NoteProxy noteProxy;
    @Autowired
    private RapportProxy rapportProxy;

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
        RapportDTO rapport = rapportProxy.getReport(Long.valueOf(id));
        model.addAttribute("rapport", rapport);
        return "Patient";
    }

    @GetMapping("/patient/add")
    public String addPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "AddPatient";
    }
    
    @PostMapping("/patient/add")
    public String addPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "AddPatient";
        }
        patientProxy.addPatient(patient);
        return "redirect:/listpatients";
    }

    @GetMapping("/patient/edit/{id}")
    public String editPatientForm(@PathVariable("id") String id, Model model) {
        Patient patient = patientProxy.getPatientById(id);
        model.addAttribute("patient", patient);
        return "EditPatient";
    }

    @PostMapping("/patient/edit/{id}")
    public String editPatient(@PathVariable("id") String id, @Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "EditPatient";
        }
        patientProxy.updatePatient(patient);
        return "redirect:/listpatients";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable("id") String id) {
        patientProxy.deletePatient(id);
        return "redirect:/listpatients";
    }

    // Note management

    @PostMapping("/note/add/{patientId}")
    public String addNote(@PathVariable("patientId") String patientId, @Valid @ModelAttribute("note") Note note, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patient", patientProxy.getPatientById(patientId));
            return "Patient";
        }
        note.setPatientId(patientId);
        noteProxy.addNote(note);
        return "redirect:/patient/view/" + patientId;
    }

    @PostMapping("/note/update/{id}")
    public String updateNote(@PathVariable("id") String id, @Valid @ModelAttribute("note") Note note, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patient", patientProxy.getPatientById(note.getPatientId()));
            return "Patient";
        }
        noteProxy.updateNote(id, note);
        return "redirect:/patient/view/" + note.getPatientId();
    }

    @GetMapping("/note/delete/{id}/{patientId}")
    public String deleteNote(@PathVariable("id") String id, @PathVariable("patientId") String patientId) {
        noteProxy.deleteNote(id);
        return "redirect:/patient/view/" + patientId;
    }

    // @GetMapping("/patient/report/{id}")
    // public String getPatientReport(@PathVariable("id") String id, Model model) {
    //     RapportDTO rapport = rapportProxy.getReport(Long.valueOf(id));
    //     model.addAttribute("rapport", rapport);
    //     return "PatientReport";
    // }
}
