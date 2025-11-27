package fr.insatoulouse.studentsservice.service;

import fr.insatoulouse.shared.dto.CreateAvailabilityDTO;
import fr.insatoulouse.shared.dto.SessionDTO;
import fr.insatoulouse.studentsservice.model.Availability;
import fr.insatoulouse.studentsservice.model.Student;
import fr.insatoulouse.studentsservice.repository.IAvailabilityRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AvailabilityService {

    @Autowired
    private IAvailabilityRepository availabilityRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private StudentService studentService;

    @Nullable
    public Availability getAvailability(UUID id) {
        return availabilityRepository.findById(id).orElse(null);
    }

    @Nullable
    public List<Availability> getAvailabilitiesByStudent(Student student) {
        return student.getAvailabilities();
    }

    @Nullable
    public Availability addAvailabilityToStudent(CreateAvailabilityDTO availabilityDTO, UUID studentId, String token) {
        SessionDTO session = authenticationService.getUserSession(token);

        if (session == null || !session.credentialUuid().equals(studentId)) {
            throw new IllegalArgumentException("Unauthorized");
        }

        Student student = this.studentService.getStudent(studentId);

        Availability availabilityToAdd = new Availability();
        availabilityToAdd.setStartDate(availabilityDTO.startDate());
        availabilityToAdd.setEndDate(availabilityDTO.endDate());
        availabilityToAdd.setStudent(student);

        return availabilityRepository.save(availabilityToAdd);
    }

    public void removeAvailabilityFromStudent(Availability availability, Student student, String token) {
        SessionDTO session = authenticationService.getUserSession(token);

        if (session == null || !session.credentialUuid().equals(student.getUuid())) {
            throw new IllegalArgumentException("Unauthorized");
        }

        availabilityRepository.delete(availability);
    }
}
