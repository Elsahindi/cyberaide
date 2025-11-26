package fr.insatoulouse.requestsservice.dto;

import java.util.Date;

public record CreateRequestDTO(String title, String description, Date dueDate) {
}
