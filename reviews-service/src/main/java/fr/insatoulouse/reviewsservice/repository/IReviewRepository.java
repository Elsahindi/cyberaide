package fr.insatoulouse.reviewsservice.repository;

import fr.insatoulouse.reviewsservice.model.Review;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IReviewRepository extends JpaRepository<@NonNull Review, @NonNull UUID> {

    List<Review> findByHelperUUID(UUID helperUUID);

}
