package com.medilabo.microservice_rapport.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medilabo.microservice_rapport.model.Note;
import com.medilabo.microservice_rapport.model.Patient;
import com.medilabo.microservice_rapport.model.Rapport;
import com.medilabo.microservice_rapport.proxy.NoteProxy;
import com.medilabo.microservice_rapport.proxy.PatientProxy;

@Service
public class RapportService {

    @Autowired
    private NoteProxy noteProxy;

    @Autowired
    private PatientProxy patientProxy;

    private static final List<String> TRIGGERS = List.of(
            "Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Fumeuse",
            "Anormal", "Cholestérol", "Vertiges", "Rechute", "Réaction", "Anticorps");

    public int countTriggers(List<Note> notes) {
        int count = 0;
        for (Note note : notes) {
            String content = note.getContent().toLowerCase();
            for (String trigger : TRIGGERS) {
                if (content.contains(trigger.toLowerCase())) {
                    count++;
                }
            }
        }
        return count;
    }

    public Rapport generateRapport(String patientId) {
        Rapport rapport = new Rapport();

        List<Note> notes = noteProxy.getNotesByPatientId(patientId);
        Patient patient = patientProxy.getPatientById(patientId);
        int triggerCount = countTriggers(notes);

        int riskLevel = calculateRiskLevel(triggerCount, patient.getBirthDate(), patient.getGender());

        rapport.setTriggerCount(triggerCount);
        rapport.setTriggersFound(TRIGGERS.stream()
                .filter(trigger -> notes.stream()
                        .anyMatch(note -> note.getContent().toLowerCase().contains(trigger.toLowerCase())))
                .toList());
        rapport.setLevelRisk(riskLevel);
        
        

        return rapport;
    }

    public int calculatePatientAge(String birthDate) {
        if (birthDate == null || birthDate.isEmpty()) {
            throw new IllegalArgumentException("Birth date cannot be null or empty");
        }
        String[] parts = birthDate.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        LocalDate parsedBirthDate = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.now();
        return Period.between(parsedBirthDate, currentDate).getYears();
    }

    public int calculateRiskLevel(int triggerCount, String birthDate, String gender) {
        int age = calculatePatientAge(birthDate);

        // aucun risque (None)
        if (triggerCount == 0) {
            return 0; // Aucun risque
            // risque limité (Borderline)
        } else if (triggerCount >= 2 && triggerCount <= 5 && age > 30) {
            return 1; // Risque limité
            // danger (In Danger)
        } else if (age < 30) {
            if (gender.equals("M")) {
                if (triggerCount >= 3) {
                    return 2; // Danger
                    // apparition précoce (Early onset)
                } else if (triggerCount >= 5) {
                    return 3; // Apparition précoce
                }
            } else if (gender.equals("F")) {
                if (triggerCount >= 4) {
                    return 2; // Danger
                    // apparition précoce (Early onset)
                } else if (triggerCount >= 7) {
                    return 3; // Apparition précoce
                }
            }
        } else if (age > 30) {
            if (triggerCount >= 6) {
                return 2; // Danger
                // apparition précoce (Early onset)
            } else if (triggerCount >= 8) {
                return 3; // Apparition précoce
            }
        }
        return 0;
    }

}
