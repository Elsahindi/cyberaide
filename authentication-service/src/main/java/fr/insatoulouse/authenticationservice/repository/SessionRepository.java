package fr.insatoulouse.authenticationservice.repository;

import fr.insatoulouse.authenticationservice.model.Session;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<@NonNull Session, @NonNull UUID> {
    Optional<Session> findByToken(String token);
}
