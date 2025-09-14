package com.medilabo.microservice_frontend.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.medilabo.microservice_frontend.config.TestSecurityConfig;
import com.medilabo.microservice_frontend.dto.RapportDTO;
import com.medilabo.microservice_frontend.model.Patient;
import com.medilabo.microservice_frontend.proxy.NoteProxy;
import com.medilabo.microservice_frontend.proxy.PatientProxy;
import com.medilabo.microservice_frontend.proxy.RapportProxy;


@SpringBootTest(properties = {"spring.profiles.active=test"})
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PatientProxy patientProxy;

    @MockitoBean
    private NoteProxy noteProxy;

    @MockitoBean
    private RapportProxy rapportProxy;

    @Test
    void testGetPatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setLastName("Doe");
        when(patientProxy.getPatientById(Long.toString(1L))).thenReturn(patient);
        when(noteProxy.getNotesByPatientId("1")).thenReturn(Collections.emptyList());
        when(rapportProxy.getReport(1L)).thenReturn(new RapportDTO());

        mockMvc.perform(get("/patient/view/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("patient"))
                .andExpect(model().attributeExists("notes"))
                .andExpect(model().attributeExists("rapport"));
    }
    
    @Test
    void testListPatients() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setLastName("Test");
        when(patientProxy.getPatients()).thenReturn(Collections.singletonList(patient));

        mockMvc.perform(get("/listpatients"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("patients"))
                .andExpect(model().attribute("patients", hasItem(
                        allOf(
                                hasProperty("lastName", is("Test"))
                        )
                )));
    }

}