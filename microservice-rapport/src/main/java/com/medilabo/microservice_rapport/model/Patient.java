package com.medilabo.microservice_rapport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private String id;
    private String birthDate;
    private String gender;

}
