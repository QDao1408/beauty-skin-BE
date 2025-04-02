package online.beautyskin.beauty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import online.beautyskin.beauty.entity.LoyaltyPoint;

import java.util.Optional;


public interface LoyaltyPointRepository extends JpaRepository<LoyaltyPoint, Long>{
    @Override
    Optional<LoyaltyPoint> findById(Long aLong);
}
