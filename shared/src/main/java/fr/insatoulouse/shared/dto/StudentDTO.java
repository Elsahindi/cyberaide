package fr.insatoulouse.shared.dto;

import fr.insatoulouse.shared.enums.Field;
import fr.insatoulouse.shared.enums.School;

import java.util.UUID;

public record StudentDTO(UUID id, String firstName, String lastName, School school, Field field) {
}


