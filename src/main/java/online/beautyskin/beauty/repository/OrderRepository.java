package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
