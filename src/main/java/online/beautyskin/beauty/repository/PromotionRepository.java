package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {


    List<Promotion> findAllByIsDeletedFalse();

    Promotion findByIdAndIsDeletedFalseAndIsOutDateFalse(long id);

    List<Promotion> findAllByIsOutDateFalseAndIsDeletedFalse();
}
