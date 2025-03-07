package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.SkinType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkinTypeRepository extends JpaRepository<SkinType, Long> {

    SkinType findById(long id);
    List<SkinType> findByIsDeletedFalse();
    SkinType findByIdAndIsDeletedFalse(long id);
}
