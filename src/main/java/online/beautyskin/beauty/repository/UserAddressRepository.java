package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    UserAddress findById(long id);
    List<UserAddress> findByIsDeletedFalse();

//    @Query("SELECT new online.beautyskin.beauty.entity.respone.UserAddressResponse(a.id, a.user.id, a.receiverAddress, a.city, a.district, " +
//            "a.ward, a.receiverName, a.receiverPhone) FROM UserAddress a WHERE a.user.id = :userId")
//    List<UserAddressResponse> findByIdAndIsDeletedFalse(@Param("userId") Long userId);

    List<UserAddress> findByUserIdAndIsDeletedFalse(long id);

    Optional<UserAddress> findFirstByUserIdAndIsDeletedFalse(Long userId);
}
