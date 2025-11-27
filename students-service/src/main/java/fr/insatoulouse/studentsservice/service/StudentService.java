package fr.insatoulouse.studentsservice.service;

import fr.insatoulouse.shared.dto.CreateStudentDTO;
import fr.insatoulouse.shared.dto.SessionDTO;
import fr.insatoulouse.shared.dto.StudentDTO;
import fr.insatoulouse.shared.dto.UpdateStudentDTO;
import fr.insatoulouse.studentsservice.model.Student;
import fr.insatoulouse.studentsservice.repository.StudentRepository;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private AuthenticationService authenticationService;

    @Nullable
    public Student getStudent(UUID id) {

        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(StudentDTO studentDTO) {
        Student student = new Student();

        student.setUuid(studentDTO.uuid());
        student.setFirstName(studentDTO.firstName());
        student.setLastName(studentDTO.lastName());
        student.setSchool(studentDTO.school());
        student.setField(studentDTO.field());

        return studentRepository.save(student);
    }

    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(UUID uuid, UpdateStudentDTO student, String token) {
        SessionDTO session = authenticationService.getUserSession(token);

        if (session == null || !session.credentialUuid().equals(uuid)) {
            throw new IllegalArgumentException("Unauthorized");
        }

        try {
            Student existingStudent = getStudent(uuid);
            Objects.requireNonNull(existingStudent).setFirstName(student.firstName());
            existingStudent.setLastName(student.lastName());
            existingStudent.setSchool(student.school());
            existingStudent.setField(student.field());

            return studentRepository.save(existingStudent);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unauthorized");
        }
    }
}
