package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedBackRepository extends JpaRepository<Feedback,Long> {
    Feedback findFeedbackById(long id);
    List<Feedback> findByIsDeleteFalse();
}
