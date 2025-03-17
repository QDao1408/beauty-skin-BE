package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByIdAndIsDeletedFalse(long id);

    List<Image> findByIsDeletedFalse();

    Optional<Image> findByUrl(String url);

}
