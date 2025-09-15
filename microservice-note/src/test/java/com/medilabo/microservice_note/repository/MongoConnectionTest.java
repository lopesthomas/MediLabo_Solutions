package com.medilabo.microservice_note.repository;

import com.medilabo.microservice_note.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MongoConnectionTest {
    @Autowired
    private NoteRepository noteRepository;

    @Test
    void testMongoConnectionAndSave() {
        Note note = new Note();
        note.setPatientId("01");
        note.setContent("Connection test");
        Note saved = noteRepository.save(note);
        assertNotNull(saved.getId());
        
        noteRepository.deleteById(saved.getId());
        assertFalse(noteRepository.findById(saved.getId()).isPresent());
    }

}
