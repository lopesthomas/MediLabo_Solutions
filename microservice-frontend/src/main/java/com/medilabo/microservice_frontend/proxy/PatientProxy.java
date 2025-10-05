package com.medilabo.microservice_frontend.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.medilabo.microservice_frontend.config.FeignConfig;
import com.medilabo.microservice_frontend.model.Patient;

@FeignClient(name = "patient-service", url = "http://microservice-gateway:8090", configuration = FeignConfig.class)
public interface PatientProxy {
    @GetMapping("/api/patients/list")
    List<Patient> getPatients();

    @GetMapping("/api/patients/{id}")
    Patient getPatientById(@PathVariable("id") String id);

    @PostMapping("/api/patients/add")
    Patient addPatient(Patient patient);

    @PostMapping("/api/patients/update")
    Patient updatePatient(@RequestBody Patient patient);

    @DeleteMapping("/api/patients/delete/{id}")
    void deletePatient(@PathVariable("id") String id);
}
