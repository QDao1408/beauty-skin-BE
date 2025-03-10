package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.CartDetails;
import online.beautyskin.beauty.entity.CustomerCart;
import online.beautyskin.beauty.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Long> {
    Optional<CartDetails> findByCustomerCartAndProduct(CustomerCart cart, Product product);
}
