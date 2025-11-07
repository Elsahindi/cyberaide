package fr.insatoulouse.authenticationservice.service;

import fr.insatoulouse.authenticationservice.model.Session;
import fr.insatoulouse.authenticationservice.repository.SessionRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SessionService {

    private static final int TOKEN_LENGTH = 128;

    private SessionRepository sessionRepository;

    private String generateRandomToken() {
        StringBuilder token = new StringBuilder(TOKEN_LENGTH);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < TOKEN_LENGTH; i++) {
            int index = (int) (Math.random() * characters.length());
            token.append(characters.charAt(index));
        }

        return token.toString();
    }

    @Nullable
    public Session getSession(String token) {
        Session session = sessionRepository.findByToken(token).orElse(null);

        if (session == null) return null;

        if (session.getExpiresAt().after(new Date())) {
            return session;
        }

        // Session expired
        sessionRepository.delete(session);

        return null;
    }
}
