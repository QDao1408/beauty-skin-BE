package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    
}
