package com.medilabo.microservice_note.dto;

import lombok.Data;

@Data
public class Patient {
    private String id;
    private String name;
    private String email;
    private String phone;
}
