package com.medilabo.microservice_rapport.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medilabo.microservice_rapport.config.FeignConfig;
import com.medilabo.microservice_rapport.model.Note;

@FeignClient(name = "note-service", url = "http://microservice-gateway:8090", configuration = FeignConfig.class)
public interface NoteProxy {

    @GetMapping("/api/notes/{patientId}")
    List<Note> getNotesByPatientId(@PathVariable String patientId);

}
