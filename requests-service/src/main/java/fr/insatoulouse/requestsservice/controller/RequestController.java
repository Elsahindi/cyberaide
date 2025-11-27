package fr.insatoulouse.requestsservice.controller;

import fr.insatoulouse.requestsservice.service.AuthenticationService;
import fr.insatoulouse.requestsservice.service.RequestService;
import fr.insatoulouse.requestsservice.service.StudentService;
import fr.insatoulouse.shared.dto.*;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private StudentService studentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<RequestDTO>> index() {
        List<RequestDTO> requests = requestService.getAll().stream()
                .map(request -> new RequestDTO(
                        request.getUuid(),
                        request.getTitle(),
                        request.getDescription(),
                        request.getRequesterUuid(),
                        request.getDueDate(),
                        request.getStatus(),
                        request.getCreatedAt()
                ))
                .toList();

        return ResponseEntity.ok(requests);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<PairDTO<RequestDTO, List<StudentDTO>>> show(@PathVariable UUID id) {
        var request = requestService.getById(id);
        if (request == null) {
            return ResponseEntity.notFound().build();
        }

        List<StudentDTO> studentDTOS = this.studentService.getStudentsWithSkills(List.of(request.getKeyword()));
        studentDTOS = studentDTOS != null ? studentDTOS.stream().filter(StudentDTO::isHelper).toList() : new ArrayList<>();

        RequestDTO requestDTO = new RequestDTO(
                request.getUuid(),
                request.getTitle(),
                request.getDescription(),
                request.getRequesterUuid(),
                request.getDueDate(),
                request.getStatus(),
                request.getCreatedAt()
        );

        return ResponseEntity.ok(new PairDTO<>(requestDTO, studentDTOS));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<RequestDTO> store(@RequestBody CreateRequestDTO dto, @RequestHeader("Authorization") String token) {
        var userId = Objects.requireNonNull(authenticationService.getUserSession(token)).credentialUuid();
        var request = requestService.create(dto, userId);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(request.getUuid()).toUri()).body(new RequestDTO(
                request.getUuid(),
                request.getTitle(),
                request.getDescription(),
                request.getRequesterUuid(),
                request.getDueDate(),
                request.getStatus(),
                request.getCreatedAt()
        ));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<RequestDTO> update(@PathVariable UUID id, @RequestBody UpdateRequestDTO dto) {
        var request = requestService.update(dto, id);

        return ResponseEntity.ok(new RequestDTO(
                request.getUuid(),
                request.getTitle(),
                request.getDescription(),
                request.getRequesterUuid(),
                request.getDueDate(),
                request.getStatus(),
                request.getCreatedAt()
        ));
    }
}
