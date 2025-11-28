package fr.insatoulouse.reviewsservice.service;

import fr.insatoulouse.reviewsservice.model.Review;
import fr.insatoulouse.reviewsservice.repository.IReviewRepository;
import fr.insatoulouse.shared.dto.CreateReviewDTO;
import fr.insatoulouse.shared.dto.SessionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    @Autowired
    private IReviewRepository reviewRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public List<Review> findByHelperUUID(UUID helperUUID) {
        return reviewRepository.findByHelperUUID(helperUUID);
    }

    public Review createReview(CreateReviewDTO createReviewDTO, UUID helperUUID, String token) {
        SessionDTO sessionDTO = this.authenticationService.getUserSession(token);

        if (sessionDTO == null) {
            throw new IllegalArgumentException("Unauthorized");
        }

        UUID authorUUID = sessionDTO.credentialUuid();

        Review review = new Review();
        review.setHelperUUID(helperUUID);
        review.setAuthorUUID(authorUUID);
        review.setRequestUUID(createReviewDTO.requestUUID());
        review.setRating(createReviewDTO.rating());
        review.setComment(createReviewDTO.comment());

        return reviewRepository.save(review);
    }

}
