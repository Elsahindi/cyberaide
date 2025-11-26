package fr.insatoulouse.studentsservice.dto;

import fr.insatoulouse.studentsservice.model.Field;
import fr.insatoulouse.studentsservice.model.School;

import java.util.UUID;

public record UpdateStudentDTO(String firstName, String lastName, School school, Field field) {
}


