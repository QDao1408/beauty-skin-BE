package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(long id);

    Order findOrderById(long id);
}
