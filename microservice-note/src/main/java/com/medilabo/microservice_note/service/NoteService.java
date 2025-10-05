package com.medilabo.microservice_note.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medilabo.microservice_note.model.Note;
import com.medilabo.microservice_note.proxy.PatientProxy;
import com.medilabo.microservice_note.repository.NoteRepository;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private PatientProxy patientProxy;

    public NoteService(NoteRepository noteRepository, PatientProxy patientProxy) {
        this.noteRepository = noteRepository;
        this.patientProxy = patientProxy;
    }

    public Note addNote(String patientId, String content){
        
        if(patientProxy.getPatientById(patientId) == null){
            throw new RuntimeException("Patient Id not found");
        }

        Note note = new Note();
        note.setPatientId(patientId);
        note.setContent(content);
        note.setCreatedAt(ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime());
        return noteRepository.save(note);
    }

    public List<Note> getNotesByPatientId(String patientId) {
        return noteRepository.findByPatientId(patientId);
    }

    public void deleteNote(String id) {
        noteRepository.deleteById(id);
    }

    public void updateNote(String id, Note note) {
        Note existingNote = noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        existingNote.setContent(note.getContent());
        noteRepository.save(existingNote);
    }

}
