package fr.insatoulouse.studentsservice.controller;

import fr.insatoulouse.studentsservice.dto.CreateStudentDTO;
import fr.insatoulouse.studentsservice.dto.StudentDTO;
import fr.insatoulouse.studentsservice.model.Student;
import fr.insatoulouse.studentsservice.service.StudentService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<StudentDTO> show(@PathVariable String id) {
        Student student = studentService.getStudent(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getSchool(),
                student.getField()
        ));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<StudentDTO> create(@RequestBody CreateStudentDTO dto) {
        Student student = studentService.createStudent(dto);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(student.getId()).toUri()).body(new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getSchool(),
                student.getField()
        ));
    }
}
