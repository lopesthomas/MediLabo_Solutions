package com.medilabo.microservice_rapport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.microservice_rapport.model.Rapport;
import com.medilabo.microservice_rapport.service.RapportService;


@RestController
public class RapportController {
    @Autowired
    private RapportService rapportService;

    @GetMapping("/report/{patientId}")
    public Rapport getRapport(@PathVariable String patientId) {
        return rapportService.generateRapport(patientId);
    }
    

}
