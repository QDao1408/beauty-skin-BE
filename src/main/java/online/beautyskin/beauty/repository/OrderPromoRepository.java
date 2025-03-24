package online.beautyskin.beauty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.OrderPromo;


public interface OrderPromoRepository extends JpaRepository<OrderPromo, Long>{
    Optional<OrderPromo> findByOrder(Order order);
}
