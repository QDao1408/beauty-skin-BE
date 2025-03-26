package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Routine;
import online.beautyskin.beauty.entity.RoutineStep;
import online.beautyskin.beauty.entity.request.RoutineRequest;
import online.beautyskin.beauty.repository.RoutineRepository;
import online.beautyskin.beauty.repository.RoutineStepRepository;
import online.beautyskin.beauty.repository.SkinTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoutineService {
    @Autowired
    private RoutineRepository repository;

    @Autowired
    private SkinTypeRepository skinTypeRepository;

    @Autowired
    private RoutineStepRepository stepRepository;

    public Routine save(RoutineRequest request) {
        Routine routine = new Routine();
        routine.setDescription(request.getDescription());
        routine.setName(request.getName());
        routine.setSkinType(skinTypeRepository.findById(request.getSkinTypeId()).orElseThrow());
        routine.setLastUpdate(LocalDateTime.now());
        return repository.save(routine);
    }

    public Routine delete(long id) {
        Routine routine = repository.findById(id);
        routine.setDeleted(true);
        routine.setLastUpdate(LocalDateTime.now());
        return repository.save(routine);
    }

    public Routine update(long id, RoutineRequest request) {
        Routine routine = repository.findById(id);
        routine.setName(request.getName());
        routine.setDescription(request.getDescription());
        routine.setSkinType(skinTypeRepository.findById(request.getSkinTypeId()).orElseThrow());
        routine.setLastUpdate(LocalDateTime.now());
        return repository.save(routine);
    }

    public List<Routine> getAll() {
        return repository.findAllWithSteps();
    }



    public List<RoutineStep> addStep(List<Long> stepId) {
        List<RoutineStep> steps = new ArrayList<>();
        if(!stepId.isEmpty()) {
            for(long id : stepId) {
                steps.add(stepRepository.findByIdAndIsDeletedFalse(id));
            }
        }
        return steps;
    }
}
