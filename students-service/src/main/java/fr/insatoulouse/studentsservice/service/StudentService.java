package fr.insatoulouse.studentsservice.service;

import fr.insatoulouse.studentsservice.dto.CreateStudentDTO;
import fr.insatoulouse.studentsservice.dto.StudentDTO;
import fr.insatoulouse.studentsservice.model.Field;
import fr.insatoulouse.studentsservice.model.School;
import fr.insatoulouse.studentsservice.model.Student;
import fr.insatoulouse.studentsservice.repository.StudentRepository;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Nullable
    public Student getStudent(String id) {

        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(CreateStudentDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.firstName());
        student.setLastName(studentDTO.lastName());
        student.setSchool(studentDTO.school());
        student.setField(studentDTO.field());
        return studentRepository.save(student);
    }
}
