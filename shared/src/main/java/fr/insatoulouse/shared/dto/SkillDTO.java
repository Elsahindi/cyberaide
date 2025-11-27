package fr.insatoulouse.shared.dto;

import java.util.UUID;

public record SkillDTO(UUID id, String title, StudentDTO student) {
}
