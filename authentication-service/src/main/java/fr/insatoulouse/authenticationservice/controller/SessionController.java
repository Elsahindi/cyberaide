package fr.insatoulouse.authenticationservice.controller;

import fr.insatoulouse.authenticationservice.dto.CreateSessionDTO;
import fr.insatoulouse.authenticationservice.dto.SessionDTO;
import fr.insatoulouse.authenticationservice.model.Credential;
import fr.insatoulouse.authenticationservice.model.Session;
import fr.insatoulouse.authenticationservice.service.CredentialService;
import fr.insatoulouse.authenticationservice.service.SessionService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController("/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<SessionDTO> show(@RequestHeader(value = "Authorization") String token) {
        token = token.replace("Bearer ", "");
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
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
