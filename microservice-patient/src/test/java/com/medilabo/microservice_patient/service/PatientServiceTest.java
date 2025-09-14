package com.medilabo.microservice_patient.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.medilabo.microservice_patient.model.Patient;
import com.medilabo.microservice_patient.repository.PatientRepository;

class PatientServiceTest {

    @Test
    void testGetPatientById() {
        PatientRepository repo = mock(PatientRepository.class);
        PatientService service = new PatientService(repo);
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setLastName("Test");
        when(repo.findById(1L)).thenReturn(Optional.of(patient));

        Patient result = service.getPatientById(1L);
        assertNotNull(result);
        assertEquals("Test", result.getLastName());
    }

    @Test
    void testGetPatientByIdNotFound() {
        PatientRepository repo = mock(PatientRepository.class);
        PatientService service = new PatientService(repo);
        when(repo.findById(1L)).thenReturn(Optional.empty());

        Patient result = service.getPatientById(1L);
        assertNull(result);
    }

    @Test
    void testSavePatient() {
        PatientRepository repo = mock(PatientRepository.class);
        PatientService service = new PatientService(repo);
        Patient patient = new Patient();
        patient.setLastName("Test");
        when(repo.save(patient)).thenReturn(patient);

        Patient result = service.savePatient(patient);
        assertNotNull(result);
        assertEquals("Test", result.getLastName());
    }

    @Test
    void testUpdatePatient() {
        PatientRepository repo = mock(PatientRepository.class);
        PatientService service = new PatientService(repo);
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setLastName("Test");
        when(repo.existsById(1L)).thenReturn(true);
        when(repo.save(patient)).thenReturn(patient);

        Patient result = service.updatePatient(patient);
        assertNotNull(result);
        assertEquals("Test", result.getLastName());
    }

    @Test
    void testUpdatePatientNotFound() {
        PatientRepository repo = mock(PatientRepository.class);
        PatientService service = new PatientService(repo);
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setLastName("Test");
        when(repo.existsById(1L)).thenReturn(false);

        Patient result = service.updatePatient(patient);
        assertNull(result);
    }

    @Test
    void testDeletePatient() {
        PatientRepository repo = mock(PatientRepository.class);
        PatientService service = new PatientService(repo);

        service.deletePatient(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}