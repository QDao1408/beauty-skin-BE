package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.UserAddress;
import online.beautyskin.beauty.entity.respone.UserAddressResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    UserAddress findById(long id);
    List<UserAddress> findByIsDeletedFalse();

    @Query("SELECT new online.beautyskin.beauty.entity.respone.UserAddressResponse(a.id, a.user.id, a.receiverAddress, a.city, a.district, " +
            "a.ward, a.receiverName, a.receiverPhone) FROM UserAddress a WHERE a.user.id = :userId")
    List<UserAddressResponse> findByIdAndIsDeletedFalse(@Param("userId") Long userId);



}
