package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Promotion createPromotion(LocalDate startDate, LocalDate endDate);
    Promotion findPromotionByDate(LocalDate date);
    Promotion findPromotionBetween(LocalDate startDate, LocalDate endDate);
    
}
