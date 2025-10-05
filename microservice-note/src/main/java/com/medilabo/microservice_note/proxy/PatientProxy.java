package com.medilabo.microservice_note.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medilabo.microservice_note.dto.Patient;

@FeignClient(name = "patient-service", url = "http://microservice-gateway:8090")
public interface PatientProxy {

    @GetMapping("/api/patients/{id}")
    Patient getPatientById(@PathVariable("id") String id);

}
