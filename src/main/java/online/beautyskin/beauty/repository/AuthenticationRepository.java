package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationRepository extends JpaRepository<User, Long> {

    User findById(long id);

    Optional<User> findByUsername(String username);

}
