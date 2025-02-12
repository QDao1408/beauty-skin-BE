package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<User, Long> {

}
