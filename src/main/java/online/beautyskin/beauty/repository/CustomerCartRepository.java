package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.CustomerCart;
import online.beautyskin.beauty.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerCartRepository extends JpaRepository<CustomerCart, Long> {
    Optional<CustomerCart> findByUser(User user);
}
