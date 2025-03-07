package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.SkinConcern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkinConcernRepository extends JpaRepository<SkinConcern, Long> {

    SkinConcern findById(long id);

    List<SkinConcern> findByIsDeletedFalse();
}
