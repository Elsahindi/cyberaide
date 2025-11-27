package fr.insatoulouse.shared.dto;

import fr.insatoulouse.shared.enums.RequestStatus;

import java.util.Date;
import java.util.UUID;

public record RequestDTO(UUID uuid, String title, String description, UUID requesterUUID, Date dueDate, RequestStatus status, Date createdAt) {
}
