package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Routine;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    Routine findById(long id);
    List<Routine> findByIsDeletedFalse();

    Routine findByIdAndIsDeletedFalse(long id);

    @Query("SELECT DISTINCT r FROM Routine r LEFT JOIN FETCH r.routineSteps")
    List<Routine> findAllWithSteps();



}
