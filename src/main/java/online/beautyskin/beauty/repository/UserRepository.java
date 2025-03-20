package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.enums.RoleEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    Optional<User> findByUsernameAndMail(String username,String email);

    Optional<User> findByMail(String mail);

    @Query("select count(a) from User  a where a.roleEnums=:roleEnums")
    long countByRole(RoleEnums roleEnums);
}
