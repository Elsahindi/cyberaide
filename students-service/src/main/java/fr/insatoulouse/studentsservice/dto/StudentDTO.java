package fr.insatoulouse.studentsservice.dto;

import fr.insatoulouse.studentsservice.model.Field;
import fr.insatoulouse.studentsservice.model.School;

public record StudentDTO(int id, String firstName, String lastName, School school, Field field) {
}


