package fr.insatoulouse.reviewsservice.controller;

import fr.insatoulouse.reviewsservice.model.Review;
import fr.insatoulouse.reviewsservice.service.ReviewService;
import fr.insatoulouse.shared.dto.CreateReviewDTO;
import fr.insatoulouse.shared.dto.ReviewDTO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/student/{id}/reviews")
    public ResponseEntity<@NonNull List<ReviewDTO>> index(@PathVariable UUID id) {
        List<Review> reviews = reviewService.findByHelperUUID(id);

        return ResponseEntity.ok().body(reviews.stream().map(review -> new ReviewDTO(
                review.getUuid(),
                review.getRequestUUID(),
                review.getHelperUUID(),
                review.getAuthorUUID(),
                review.getRating(),
                review.getComment()
        )).toList());
    }

    @PostMapping("/student/{id}/reviews")
    public ResponseEntity<@NonNull ReviewDTO> create(@PathVariable UUID id, @RequestBody CreateReviewDTO dto, @RequestHeader("Authorization") String token) {
        Review review = reviewService.createReview(dto, id, token);

        if (review == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new ReviewDTO(
                review.getUuid(),
                review.getRequestUUID(),
                review.getHelperUUID(),
                review.getAuthorUUID(),
                review.getRating(),
                review.getComment()
        ));
    }

}
