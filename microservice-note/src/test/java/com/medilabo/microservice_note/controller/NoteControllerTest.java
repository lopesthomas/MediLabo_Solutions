package com.medilabo.microservice_note.controller;

import com.medilabo.microservice_note.config.TestSecurityConfig;
import com.medilabo.microservice_note.model.Note;
import com.medilabo.microservice_note.repository.NoteRepository;
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
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NoteRepository noteRepository;

    private String noteId;

    @BeforeEach
    void setup() {
        noteRepository.deleteAll();
        Note note = new Note();
        note.setContent("Test note");
        Note saved = noteRepository.save(note);
        noteId = saved.getId();
        saved.setPatientId("123");
        noteRepository.save(saved);

    }

    @Test
    void testGetNoteByPatientId() throws Exception {
        org.junit.jupiter.api.Assertions.assertNotNull(noteId);

        mockMvc.perform(get("/api/notes/123")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content", is("Test note")));
    }

    @Test
    void testGetNoteByPatientIdNotFound() throws Exception {
        mockMvc.perform(get("/api/notes/999")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    void testListNotes() throws Exception {
        Note note = new Note();
        note.setContent("Test note 2");
        note.setPatientId("123");
        noteRepository.save(note);

        mockMvc.perform(get("/api/notes/123")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].content", is("Test note")))
                .andExpect(jsonPath("$[1].content", is("Test note 2")));
    }

}