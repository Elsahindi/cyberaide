package fr.insatoulouse.studentsservice.controller;

import fr.insatoulouse.shared.dto.StudentDTO;
import fr.insatoulouse.shared.dto.UpdateStudentDTO;
import fr.insatoulouse.studentsservice.model.Student;
import fr.insatoulouse.studentsservice.service.StudentService;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<StudentDTO>> index(@QueryParam("skills") String skills) {
        List<String> skillsList = skills != null && !skills.isEmpty() ? List.of(skills.split(",")) : List.of();
        return ResponseEntity.ok(this.studentService.getAllStudentsWithSkills(skillsList).stream().map(student -> new StudentDTO(
                student.getUuid(),
                student.getFirstName(),
                student.getLastName(),
                student.getSchool(),
                student.getField(),
                student.isHelper()
        )).toList());
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<StudentDTO> show(@PathVariable UUID id) {
        Student student = studentService.getStudent(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new StudentDTO(
                student.getUuid(),
                student.getFirstName(),
                student.getLastName(),
                student.getSchool(),
                student.getField(),
                student.isHelper()
        ));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> create(@RequestBody StudentDTO dto) {
        Student student = studentService.createStudent(dto);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(student.getUuid()).toUri()).body(new StudentDTO(
                student.getUuid(),
                student.getFirstName(),
                student.getLastName(),
                student.getSchool(),
                student.getField(),
                student.isHelper()
        ));
    }

    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON)
    public Object delete(@PathVariable UUID id, @RequestHeader("Authorization") String token) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        this.studentService.deleteStudent(student.getUuid(), token);

        return ResponseEntity.noContent();
    }

    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<StudentDTO> update(@PathVariable UUID id, @RequestBody UpdateStudentDTO dto, @RequestHeader("Authorization") String token) {
        Student existingStudent = studentService.getStudent(id);

        if (existingStudent == null) {
            return ResponseEntity.notFound().build();
        }

        Student student = studentService.updateStudent(id, dto, token);

        return ResponseEntity.ok(new StudentDTO(
                student.getUuid(),
                student.getFirstName(),
                student.getLastName(),
                student.getSchool(),
                student.getField(),
                student.isHelper()
        ));
    }
}