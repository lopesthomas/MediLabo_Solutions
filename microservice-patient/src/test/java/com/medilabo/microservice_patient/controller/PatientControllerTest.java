package com.medilabo.microservice_patient.controller;

import com.medilabo.microservice_patient.config.TestSecurityConfig;
import com.medilabo.microservice_patient.model.Gender;
import com.medilabo.microservice_patient.model.Patient;
import com.medilabo.microservice_patient.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
@ActiveProfiles("test")
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setup() {
        patientRepository.deleteAll();
        Patient patient = new Patient();
        //patient.setId(1L);
        patient.setLastName("Test");
        patient.setFirstName("Testy");
        patient.setGender(Gender.M);
        patient.setBirthDate("1990-01-01");
        patientRepository.save(patient);
    }

    @Test
    void testGetPatientById() throws Exception {
        mockMvc.perform(get("/api/patients/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", is("Test")));
    }

    @Test
    void testGetPatientByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/patients/999")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void testListPatients() throws Exception {
        mockMvc.perform(get("/api/patients/list")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].lastName", is("Test")));
    }

}