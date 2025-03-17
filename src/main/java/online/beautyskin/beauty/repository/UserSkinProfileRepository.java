package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.UserSkinProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSkinProfileRepository extends JpaRepository<UserSkinProfile, Long> {
    UserSkinProfile findByUser(User user);
}
