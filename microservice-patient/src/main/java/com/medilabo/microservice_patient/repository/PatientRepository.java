package com.medilabo.microservice_patient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medilabo.microservice_patient.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findAll();

}
