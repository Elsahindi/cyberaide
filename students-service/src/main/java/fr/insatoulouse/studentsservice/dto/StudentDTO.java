package fr.insatoulouse.studentsservice.dto;

import fr.insatoulouse.studentsservice.model.Field;
import fr.insatoulouse.studentsservice.model.School;

import java.util.UUID;

public record StudentDTO(UUID id, String firstName, String lastName, School school, Field field) {
}


