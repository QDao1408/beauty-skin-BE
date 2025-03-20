package online.beautyskin.beauty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import online.beautyskin.beauty.entity.LoyaltyPoint;

public interface LoyaltyPointRepository extends JpaRepository<LoyaltyPoint, Long>{

    double findAmountLevelById(int i);
    
}
