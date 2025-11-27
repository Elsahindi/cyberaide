package fr.insatoulouse.studentsservice.service;

import fr.insatoulouse.shared.dto.SessionDTO;
import fr.insatoulouse.shared.dto.StudentDTO;
import fr.insatoulouse.shared.dto.UpdateStudentDTO;
import fr.insatoulouse.studentsservice.model.Student;
import fr.insatoulouse.studentsservice.repository.IStudentRepository;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class StudentService {

    private IStudentRepository studentRepository;

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
        student.setHelper(studentDTO.isHelper());

        return studentRepository.save(student);
    }

    public List<Student> getAllStudentsWithSkills(List<String> skills) {
        if (skills == null || skills.isEmpty()) {
            return studentRepository.findAll();
        } else {
            return studentRepository.findDistinctBySkills_TitleIn(skills);
        }
    }

    public void deleteStudent(UUID uuid, String token) {
        SessionDTO session = authenticationService.getUserSession(token);

        if (session == null || !session.credentialUuid().equals(uuid)) {
            throw new IllegalArgumentException("Unauthorized");
        }

        studentRepository.deleteById(uuid);
    }

    public Student updateStudent(UUID uuid, UpdateStudentDTO studentDTO, String token) {
        SessionDTO session = authenticationService.getUserSession(token);

        if (session == null || !session.credentialUuid().equals(uuid)) {
            throw new IllegalArgumentException("Unauthorized");
        }

        try {
            Student existingStudent = getStudent(uuid);
            Objects.requireNonNull(existingStudent).setFirstName(studentDTO.firstName());
            existingStudent.setLastName(studentDTO.lastName());
            existingStudent.setSchool(studentDTO.school());
            existingStudent.setField(studentDTO.field());
            existingStudent.setHelper(studentDTO.isHelper());

            return studentRepository.save(existingStudent);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unauthorized");
        }
    }
}
