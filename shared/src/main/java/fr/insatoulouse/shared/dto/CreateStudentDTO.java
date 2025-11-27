package fr.insatoulouse.shared.dto;

import fr.insatoulouse.shared.enums.Field;
import fr.insatoulouse.shared.enums.School;

public record CreateStudentDTO(String firstName, String lastName, School school, Field field, boolean isHelper) {
}
