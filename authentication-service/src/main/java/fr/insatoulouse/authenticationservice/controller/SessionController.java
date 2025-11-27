package fr.insatoulouse.authenticationservice.controller;

import fr.insatoulouse.authenticationservice.model.Session;
import fr.insatoulouse.authenticationservice.service.SessionService;
import fr.insatoulouse.shared.dto.CreateSessionDTO;
import fr.insatoulouse.shared.dto.SessionDTO;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<SessionDTO> show(@RequestHeader(value = "Authorization") String token) {
        token = token.replace("Bearer ", "");
        System.out.println(token);
        Session session = sessionService.getSession(token);

        if (session == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new SessionDTO(
                session.getCredentialUuid(),
                session.getToken(),
                session.getExpiresAt()
        ));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<SessionDTO> create(@RequestBody CreateSessionDTO dto) {
        Session session = sessionService.createSession(dto);

        if (session == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(new SessionDTO(
                session.getCredentialUuid(),
                session.getToken(),
                session.getExpiresAt()
        ));
    }
}
