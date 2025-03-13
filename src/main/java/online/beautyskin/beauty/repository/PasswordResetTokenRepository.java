package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.PasswordResetToken;
import online.beautyskin.beauty.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    Optional<PasswordResetToken> findByUser(User user);

    Optional<PasswordResetToken> findTopByUserOrderByExpiredTimeDesc(User user);

    Optional<User> findUserByToken(String token);



}
