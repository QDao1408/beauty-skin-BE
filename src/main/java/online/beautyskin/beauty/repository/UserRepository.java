package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.LoyaltyPoint;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.enums.RoleEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    Optional<User> findByUsernameAndMail(String username,String email);

    Optional<User> findByMail(String mail);


    List<User> findByLoyaltyPointAndIsDeletedFalse(LoyaltyPoint loyaltyPoint);


    @Query("select count(a) from User  a where a.roleEnums=:roleEnums")
    long countByRole(RoleEnums roleEnums);

    @Query("SELECT u FROM User u WHERE u.roleEnums = :roleEnums")
    List<User> findAllStaff(@Param("roleEnums") RoleEnums roleEnums);

}
