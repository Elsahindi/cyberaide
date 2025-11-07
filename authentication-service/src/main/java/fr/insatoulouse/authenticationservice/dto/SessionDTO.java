package fr.insatoulouse.authenticationservice.dto;

import java.util.Date;
import java.util.UUID;

public record SessionDTO(UUID credentialUuid, String token, Date expiresAt) {
}
