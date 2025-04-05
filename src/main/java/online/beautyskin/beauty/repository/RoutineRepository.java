package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    Routine findById(long id);
    List<Routine> findByIsDeletedFalse();

    Routine findByIdAndIsDeletedFalse(long id);
    @Query("SELECT DISTINCT r FROM Routine r LEFT JOIN FETCH r.routineSteps")
    List<Routine> findAllWithSteps();

    Routine findBySkinTypeIdAndIsDeletedFalse(Long skinTypeId);

}
