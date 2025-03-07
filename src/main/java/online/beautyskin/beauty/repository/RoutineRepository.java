package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    Routine findById(long id);
    List<Routine> findByIsDeletedFalse();
}
