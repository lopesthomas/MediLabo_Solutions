package com.medilabo.microservice_frontend.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.medilabo.microservice_frontend.model.Note;

@FeignClient(name = "note-service", url = "http://localhost:8080")
public interface NoteProxy {

    @PostMapping("/api/notes/add")
    Note addNote(@RequestBody Note note);

    @GetMapping("/api/notes/{patientId}")
    List<Note> getNotesByPatientId(@PathVariable String patientId);

    @PutMapping("/api/notes/update/{id}")
    String updateNote(@PathVariable String id, @RequestBody Note note);

    @DeleteMapping("/api/notes/delete/{id}")
    void deleteNote(@PathVariable String id);
}
