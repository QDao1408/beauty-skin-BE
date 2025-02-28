package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    UserAddress findById(long id);
    List<UserAddress> findByIsDeletedFalse();
    List<UserAddress> findByIdAndIsDeletedFalse(long id);
}
