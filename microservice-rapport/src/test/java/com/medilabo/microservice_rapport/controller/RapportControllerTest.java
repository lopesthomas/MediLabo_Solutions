package com.medilabo.microservice_rapport.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.medilabo.microservice_rapport.model.Rapport;
import com.medilabo.microservice_rapport.service.RapportService;

@SpringBootTest
@AutoConfigureMockMvc
class RapportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RapportService rapportService;

    @Test
    void testGetRapport() throws Exception {
        Rapport rapport = new Rapport();
        rapport.setLevelRisk(1);
        when(rapportService.generateRapport("1")).thenReturn(rapport);

        mockMvc.perform(get("/api/report/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.levelRisk").value(1));
    }
}