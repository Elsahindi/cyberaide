package fr.insatoulouse.shared.dto;

import java.util.Date;
import java.util.UUID;

public record AvailabilityDTO(UUID uuid, Date startDate, Date endDate) {
}
