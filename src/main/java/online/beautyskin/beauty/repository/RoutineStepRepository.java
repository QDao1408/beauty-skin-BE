package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.RoutineStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineStepRepository extends JpaRepository<RoutineStep, Long> {

    RoutineStep findById(long id);

    List<RoutineStep> findByIsDeletedFalse();
    RoutineStep findByIdAndIsDeletedFalse(long id);
}
