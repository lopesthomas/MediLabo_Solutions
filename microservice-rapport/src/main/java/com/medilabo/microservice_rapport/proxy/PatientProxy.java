package com.medilabo.microservice_rapport.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medilabo.microservice_rapport.model.Patient;

@FeignClient(name = "patient-service", url = "http://localhost:8081")
public interface PatientProxy {

    @GetMapping("/patients/{id}")
    Patient getPatientById(@PathVariable("id") String id);

}
