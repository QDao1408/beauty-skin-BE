package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.RoutineStep;
import online.beautyskin.beauty.entity.request.RoutineStepRequest;
import online.beautyskin.beauty.repository.CategoryRepository;
import online.beautyskin.beauty.repository.RoutineRepository;
import online.beautyskin.beauty.repository.RoutineStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineStepService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RoutineStepRepository repository;

    @Autowired
    private RoutineRepository routineRepository;

}
