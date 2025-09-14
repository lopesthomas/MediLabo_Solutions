package com.medilabo.microservice_rapport.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.medilabo.microservice_rapport.model.Note;
import com.medilabo.microservice_rapport.model.Patient;
import com.medilabo.microservice_rapport.proxy.NoteProxy;
import com.medilabo.microservice_rapport.proxy.PatientProxy;

class RapportServiceTest {

    @Test
    void testGenerateRapport() {
        PatientProxy patientProxy = mock(PatientProxy.class);
        NoteProxy noteProxy = mock(NoteProxy.class);
        RapportService service = new RapportService(noteProxy, patientProxy);

        Patient patient = new Patient();
        patient.setId("1");
        patient.setBirthDate("1980-01-01");
        patient.setGender("M");

        Note note = new Note();
        note.setId("n1");
        note.setPatientId("1");
        note.setContent("Patient has Cholestérol, Vertiges, and is a Fumeur.");

        when(patientProxy.getPatientById("1")).thenReturn(patient);
        when(noteProxy.getNotesByPatientId("1")).thenReturn(Collections.singletonList(note));

        var rapport = service.generateRapport("1");
        assertNotNull(rapport);
        assertEquals(3, rapport.getTriggerCount());
        assertTrue(rapport.getTriggersFound().contains("Cholestérol"));
        assertEquals(1, service.calculateRiskLevel(rapport.getTriggerCount(), patient.getBirthDate(), patient.getGender()));
    }
    
    @Test
    void testCalculateRiskLevel() {
        RapportService service = new RapportService(null, null);
        // Age > 30, Male
        assertEquals(0, service.calculateRiskLevel(0, "1980-01-01", "M"));
        assertEquals(0, service.calculateRiskLevel(1, "1980-01-01", "M"));
        assertEquals(1, service.calculateRiskLevel(3, "1980-01-01", "M"));
        assertEquals(2, service.calculateRiskLevel(6, "1980-01-01", "M"));
        assertEquals(3, service.calculateRiskLevel(8, "1980-01-01", "M"));

        // Age < 30, Female
        assertEquals(0, service.calculateRiskLevel(0, "2015-01-01", "F"));
        assertEquals(0, service.calculateRiskLevel(2, "2015-01-01", "F"));
        assertEquals(2, service.calculateRiskLevel(6, "2015-01-01", "F"));
        assertEquals(3, service.calculateRiskLevel(8, "2015-01-01", "F"));

        // Age < 30, Male
        assertEquals(0, service.calculateRiskLevel(0, "2015-01-01", "M"));
        assertEquals(0, service.calculateRiskLevel(2, "2015-01-01", "M"));
        assertEquals(2, service.calculateRiskLevel(3, "2015-01-01", "M"));
        assertEquals(3, service.calculateRiskLevel(8, "2015-01-01", "M"));
    }

    @Test
    void testBirthDateisEmpty() {
        RapportService service = new RapportService(null, null);
        assertThrows(IllegalArgumentException.class, () -> service.calculateRiskLevel(5, null, "M"));
        assertThrows(IllegalArgumentException.class, () -> service.calculateRiskLevel(5, "", "M"));

    }
}