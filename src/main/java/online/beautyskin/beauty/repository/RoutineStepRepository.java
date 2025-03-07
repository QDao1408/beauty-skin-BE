package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.RoutineStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineStepRepository extends JpaRepository<RoutineStep, Long> {

    RoutineStep findById(long id);


}
