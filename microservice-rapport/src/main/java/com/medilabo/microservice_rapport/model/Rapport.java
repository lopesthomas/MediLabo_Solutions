package com.medilabo.microservice_rapport.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rapport {
    private DiabetesRisk risk;
    private int triggerCount;
    private List<String> triggersFound;
    private int levelRisk;

}
