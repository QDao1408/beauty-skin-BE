package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findById(long id);
    List<Tag> findByIsDeletedFalse();

    Tag findByIdAndIsDeletedFalse(long id);
}
