package fr.insatoulouse.requestsservice.controller;

import fr.insatoulouse.requestsservice.service.AuthenticationService;
import fr.insatoulouse.requestsservice.service.RequestService;
import fr.insatoulouse.shared.dto.CreateRequestDTO;
import fr.insatoulouse.shared.dto.RequestDTO;
import fr.insatoulouse.shared.dto.UpdateRequestDTO;
import jakarta.ws.rs.HeaderParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private AuthenticationService authenticationService;

    private RequestService requestService;

    @GetMapping()
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

    @GetMapping("/{id}")
    public ResponseEntity<RequestDTO> show(@PathVariable UUID id) {
        var request = requestService.getById(id);
        if (request == null) {
            return ResponseEntity.notFound().build();
        }

        // TODO: Search user corresponding to keyword.

        RequestDTO requestDTO = new RequestDTO(
                request.getUuid(),
                request.getTitle(),
                request.getDescription(),
                request.getRequesterUuid(),
                request.getDueDate(),
                request.getStatus(),
                request.getCreatedAt()
        );

        return ResponseEntity.ok(requestDTO);
    }

    @PostMapping
    public ResponseEntity<RequestDTO> store(@RequestBody CreateRequestDTO dto, @HeaderParam("Authorization") String token) {
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

    @PutMapping("/{id}")
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
