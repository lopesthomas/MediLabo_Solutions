package com.medilabo.microservice_note.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "patients_notes")
public class Note {
    @Id
    private String id;
    private String patientId;
    private String content;
    private LocalDateTime createdAt;

}
