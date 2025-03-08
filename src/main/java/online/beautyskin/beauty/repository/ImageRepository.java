package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByIdAndIsDeletedFalse(long id);

    List<Image> findByIsDeletedFalse();

}
