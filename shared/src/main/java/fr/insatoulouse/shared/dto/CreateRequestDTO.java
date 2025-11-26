package fr.insatoulouse.shared.dto;

import java.util.Date;

public record CreateRequestDTO(String title, String description, Date dueDate) {
}
