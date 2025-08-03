package com.medilabo.microservice_note.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.microservice_note.model.Note;
import com.medilabo.microservice_note.service.NoteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/add")
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note.getPatientId(), note.getContent());
    }

    @GetMapping("/{patientId}")
    public List<Note> getNotesByPatientId(@PathVariable String patientId) {
        return noteService.getNotesByPatientId(patientId);
    }

    @PutMapping("update/{id}")
    public String updateNote(@PathVariable String id, @RequestBody Note note) {
        noteService.updateNote(id, note);
        return "Note updated successfully";
    }
    
    @DeleteMapping("delete/{id}")
    public void deleteNote(@PathVariable String id) {
        noteService.deleteNote(id);
    }

}
