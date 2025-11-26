package fr.insatoulouse.requestsservice.repository;

import fr.insatoulouse.requestsservice.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface IRequestRepository extends JpaRepository<Request, UUID> {
}
