package fr.insatoulouse.shared.dto;

import java.util.UUID;

public record CreateReviewDTO(UUID requestUUID, int rating, String comment) {
}
