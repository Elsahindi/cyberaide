package fr.insatoulouse.requestsservice.service;

import fr.insatoulouse.requestsservice.dto.CreateRequestDTO;
import fr.insatoulouse.requestsservice.model.Request;
import fr.insatoulouse.requestsservice.repository.IRequestRepository;
import jakarta.annotation.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Service
@RequestMapping("/requests")
public class RequestService {

    private IRequestRepository requestRepository;

    public List<Request> getAll() {
        List<Request> requests = requestRepository.findAll();

        return List.copyOf(requests);
    }

    @Nullable
    public Request getById(UUID uuid) {
        return requestRepository.findById(uuid).orElse(null);
    }

}
