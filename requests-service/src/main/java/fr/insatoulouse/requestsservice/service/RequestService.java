package fr.insatoulouse.requestsservice.service;

import fr.insatoulouse.requestsservice.model.Request;
import fr.insatoulouse.requestsservice.repository.IRequestRepository;
import fr.insatoulouse.shared.dto.CreateRequestDTO;
import fr.insatoulouse.shared.dto.StudentDTO;
import fr.insatoulouse.shared.dto.UpdateRequestDTO;
import fr.insatoulouse.shared.enums.RequestStatus;
import jakarta.annotation.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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

    public List<StudentDTO> getRecommendedHelpers(UUID requestUuid) {
        Request request = getById(requestUuid);

        return new ArrayList<>();
    }

    public Request create(CreateRequestDTO dto, UUID studentUuid) {
        Request request = new Request();

        request.setTitle(dto.title());
        request.setDescription(dto.description());
        request.setRequesterUuid(studentUuid);
        request.setStatus(RequestStatus.PENDING);
        request.setDueDate(dto.dueDate());

        return requestRepository.save(request);
    }

    public Request update(UpdateRequestDTO dto, UUID requestId) {
        Request existingRequest = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        existingRequest.setStatus(dto.status());

        return requestRepository.save(existingRequest);
    }

}
