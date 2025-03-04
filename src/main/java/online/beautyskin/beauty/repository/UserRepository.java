package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    Optional<User> findByUsernameAndMail(String username,String email);

    Optional<User> findByMail(String mail);
}
