package fr.insatoulouse.shared.dto;

import java.util.UUID;

public record ReviewDTO(UUID uuid, UUID requestUUID, UUID helperUUID, UUID authorUUID, int rating, String comment) {
}
