package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthenticationRepository extends JpaRepository<User, Long> {

    User findById(long id);

    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.isDeleted = false")
    List<User> findByIsDeletedFalse();

    Optional<User> findByMail(String email);
}
