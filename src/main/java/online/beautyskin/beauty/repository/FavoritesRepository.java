package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    Optional<Favorites> findByUserId(long id);
}
