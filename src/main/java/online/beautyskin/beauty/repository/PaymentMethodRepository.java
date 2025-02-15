package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    public PaymentMethod findByName(String name);
    public List<PaymentMethod> findAll();
    public PaymentMethod findById(long id);
    public List<PaymentMethod> findByIsDeletedFalse();
}
