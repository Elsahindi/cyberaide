package fr.insatoulouse.studentsservice.dto;

import fr.insatoulouse.studentsservice.model.Field;
import fr.insatoulouse.studentsservice.model.School;

public record CreateStudentDTO(String firstName, String lastName, School school, Field field) {
}
