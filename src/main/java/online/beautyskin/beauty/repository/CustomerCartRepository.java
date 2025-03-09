package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.CustomerCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerCartRepository extends JpaRepository<CustomerCart, Long> {
    CustomerCart findByUserId(long userId);
}
