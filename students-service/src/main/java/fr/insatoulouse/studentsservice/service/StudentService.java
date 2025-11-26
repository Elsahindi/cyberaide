package fr.insatoulouse.studentsservice.service;

import fr.insatoulouse.shared.dto.CreateStudentDTO;
import fr.insatoulouse.shared.dto.StudentDTO;
import fr.insatoulouse.studentsservice.model.Student;
import fr.insatoulouse.studentsservice.repository.StudentRepository;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

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

}
