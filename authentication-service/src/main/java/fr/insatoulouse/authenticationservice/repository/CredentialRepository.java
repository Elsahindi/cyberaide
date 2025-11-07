package fr.insatoulouse.authenticationservice.repository;

import fr.insatoulouse.authenticationservice.model.Credential;
import fr.insatoulouse.authenticationservice.model.Session;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CredentialRepository extends JpaRepository<@NonNull Credential, @NonNull UUID> {
    Optional<Credential> findByEmail(String token);
}
