package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Routine;
import online.beautyskin.beauty.entity.RoutineStep;
import online.beautyskin.beauty.entity.request.RoutineStepRequest;
import online.beautyskin.beauty.repository.RoutineStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineStepService {

    @Autowired
    private RoutineStepRepository repository;

    public RoutineStep create(RoutineStepRequest request) {
        RoutineStep step = new RoutineStep();
        step.setRoutine(request.getRoutine());
        step.setDescription(request.getDescription());
        step.setStepName(request.getStepName());
        step.setStepOrder(request.getStepOrder());
        return repository.save(step);
    }

    public RoutineStep update(long id, RoutineStepRequest request) {
        RoutineStep step = repository.findById(id);
        step.setRoutine(request.getRoutine());
        step.setDescription(request.getDescription());
        step.setStepName(request.getStepName());
        step.setStepOrder(request.getStepOrder());
        return repository.save(step);
    }

//    public RoutineStep delete(long id) {
//        RoutineStep step = repository.findById(id);
//        step.setDeleted(true);
//
//    }

    public List<RoutineStep> getAll() {
        return repository.findByIsDeletedFalse();
    }

    public RoutineStep delete(long id) {
        RoutineStep step = repository.findById(id);
        step.setDeleted(false);
        return repository.save(step);
    }


}
