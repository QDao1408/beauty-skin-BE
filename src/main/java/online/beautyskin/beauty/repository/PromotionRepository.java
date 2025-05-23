package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.UserRank;
import online.beautyskin.beauty.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {


    List<Promotion> findAllByIsDeletedFalse();

    List<Promotion> findAllByNumOfPromoIsGreaterThanAndIsOutDateFalseAndIsDeletedFalse(int numOfPromo);

    Optional<Promotion> findAllByIdAndNumOfPromoIsGreaterThanAndIsOutDateFalseAndIsDeletedFalse(long id, int numOfPromo);

    Promotion findById(long id);

    List<Promotion> findByUserRank(UserRank userRank);
}
