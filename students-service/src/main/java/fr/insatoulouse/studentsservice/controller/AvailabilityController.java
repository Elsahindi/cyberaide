package fr.insatoulouse.studentsservice.controller;

import fr.insatoulouse.shared.dto.*;
import fr.insatoulouse.studentsservice.model.Availability;
import fr.insatoulouse.studentsservice.model.Student;
import fr.insatoulouse.studentsservice.service.AvailabilityService;
import fr.insatoulouse.studentsservice.service.StudentService;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AvailabilityController {
    private final AvailabilityService availabilityService;
    private final StudentService studentService;

    @GetMapping(path = "/student/{id}/availability", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<AvailabilityDTO>> show(@PathVariable UUID id) {
        Student student = studentService.getStudent(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        List<Availability> availabilities = availabilityService.getAvailabilitiesByStudent(student);

        if (availabilities == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(availabilities.stream().map(availability -> new AvailabilityDTO(
                availability.getUuid(),
                availability.getStartDate(),
                availability.getStartDate()
        )).toList());
    }

    @PostMapping(path = "/student/{id}/availability", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<AvailabilityDTO> create(@PathVariable UUID id, @RequestBody CreateAvailabilityDTO dto, @RequestHeader("Authorization") String token) {
        Availability availability = availabilityService.addAvailabilityToStudent(dto, id, token);

        if (availability == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new AvailabilityDTO(
                availability.getUuid(),
                availability.getStartDate(),
                availability.getEndDate()
        ));
    }

    @DeleteMapping(path = "/student/{id}/availability/{availabilityId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> delete(@PathVariable UUID id, @PathVariable UUID availabilityId, @RequestHeader("Authorization") String token) {
        Availability availability = availabilityService.getAvailability(availabilityId);

        if (availability == null) {
            return ResponseEntity.notFound().build();
        }

        if (!availability.getStudent().getUuid().equals(id)) {
            return ResponseEntity.badRequest().build();
        }

        this.availabilityService.removeAvailabilityFromStudent(availability, availability.getStudent(), token);

        return ResponseEntity.noContent().build();
    }


}
