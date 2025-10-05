package com.medilabo.microservice_note.service;

import com.medilabo.microservice_note.dto.Patient;
import com.medilabo.microservice_note.model.Note;
import com.medilabo.microservice_note.proxy.PatientProxy;
import com.medilabo.microservice_note.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoteServiceTest {

    @Test
    void testGetNotesByPatientId() {
        NoteRepository repo = mock(NoteRepository.class);
        PatientProxy patientProxy = mock(PatientProxy.class);
        NoteService service = new NoteService(repo, patientProxy);
        when(repo.findByPatientId("123")).thenReturn(java.util.List.of(new Note(), new Note()));

        var notes = service.getNotesByPatientId("123");
        assertEquals(2, notes.size());
    }

    @Test
    void testAddNote() {
        NoteRepository repo = mock(NoteRepository.class);
        PatientProxy patientProxy = mock(PatientProxy.class);
        NoteService service = new NoteService(repo, patientProxy);
        Note note = new Note();
        note.setPatientId("123");
        note.setContent("New note");
        Patient patient = new Patient();
        patient.setId("123");
        when(patientProxy.getPatientById("123")).thenReturn(patient);

        when(repo.save(Mockito.any(Note.class))).thenReturn(note);

        Note result = service.addNote("123", "New note");
        assertNotNull(result);
        assertEquals("New note", result.getContent());
    }

    @Test
    void testAddNoteWithInvalidPatient() {
        NoteRepository repo = mock(NoteRepository.class);
        PatientProxy patientProxy = mock(PatientProxy.class);
        NoteService service = new NoteService(repo, patientProxy);
        when(patientProxy.getPatientById("999")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            service.addNote("999", "New note");
        });
        assertEquals("Patient Id not found", assertThrows(RuntimeException.class, () -> {
            service.addNote("999", "New note");
        }).getMessage());
    }

    @Test
    void testDeleteNote() {
        NoteRepository repo = mock(NoteRepository.class);
        PatientProxy patientProxy = mock(PatientProxy.class);
        NoteService service = new NoteService(repo, patientProxy);

        service.deleteNote("1");
        verify(repo, times(1)).deleteById("1");
    }

    @Test
    void testUpdateNote() {
        NoteRepository repo = mock(NoteRepository.class);
        PatientProxy patientProxy = mock(PatientProxy.class);
        NoteService service = new NoteService(repo, patientProxy);
        Note existingNote = new Note();
        existingNote.setId("1");
        existingNote.setContent("Old content");
        when(repo.findById("1")).thenReturn(Optional.of(existingNote));
        when(repo.save(Mockito.any(Note.class))).thenReturn(existingNote);

        Note updatedNote = new Note();
        updatedNote.setContent("Updated content");
        service.updateNote("1", updatedNote);

        verify(repo, times(1)).save(Mockito.argThat(note -> note.getContent().equals("Updated content")));
    }
}