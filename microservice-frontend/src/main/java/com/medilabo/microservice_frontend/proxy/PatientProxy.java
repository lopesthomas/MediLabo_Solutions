package com.medilabo.microservice_frontend.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.medilabo.microservice_frontend.model.Patient;

@FeignClient(name = "patient-service", url = "http://localhost:8081")
public interface PatientProxy {
    @GetMapping("/patients/list")
    List<Patient> getPatients();
}
