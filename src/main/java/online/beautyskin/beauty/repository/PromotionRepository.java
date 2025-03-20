package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query("SELECT p FROM Promotion p WHERE p.startDate <= CURRENT_DATE AND p.endDate >= CURRENT_DATE")
    List<Promotion> findValidPromotions();

    List<Promotion> findAllByIsDeletedFalse();

    Promotion findByIdAndIsDeletedFalse(long id);
}
