package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Feedback;
import online.beautyskin.beauty.entity.OrderDetail;
import online.beautyskin.beauty.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    List<Feedback> findByIsDeleteFalse();
    List<Feedback> findByProductId(long productId);
}
