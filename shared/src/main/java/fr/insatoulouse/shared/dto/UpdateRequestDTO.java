package fr.insatoulouse.shared.dto;

import fr.insatoulouse.shared.enums.RequestStatus;

public record UpdateRequestDTO(RequestStatus status) {
}
