package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.entity.Routine;
import online.beautyskin.beauty.entity.RoutineStep;
import online.beautyskin.beauty.entity.request.ProductRequestRoutine;
import online.beautyskin.beauty.entity.request.RoutineRequest;
import online.beautyskin.beauty.entity.request.RoutineStepRequest;
import online.beautyskin.beauty.repository.ProductRepository;
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
    private RoutineRepository routineRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SkinTypeRepository skinTypeRepository;

    public Routine createRoutine(RoutineRequest routineRequest) {
        // Tạo mới routine
        Routine routine = new Routine();
        routine.setName(routineRequest.getName());
        routine.setDescription(routineRequest.getDescription());
        routine.setSkinType(skinTypeRepository.findById(routineRequest.getSkinTypeId()).orElse(null));
        routine.setLastUpdate(LocalDateTime.now());

        // Lưu routine vào cơ sở dữ liệu
        routine = routineRepository.save(routine);

        // Lấy danh sách các bước trong routine
        List<RoutineStepRequest> routineStepRequests = routineRequest.getRoutineStepRequests();
        List<RoutineStep> routineSteps = new ArrayList<>();

        for (RoutineStepRequest routineStepRequest : routineStepRequests) {
            RoutineStep routineStep = new RoutineStep();
            routineStep.setDescription(routineStepRequest.getDescription());
            routineStep.setStepName(routineStepRequest.getStepName());
            routineStep.setStepOrder(routineStepRequest.getStepOrder());
            routineStep.setRoutine(routine);

            // Danh sách sản phẩm cho mỗi bước
            List<ProductRequestRoutine> productRequestRoutines = routineStepRequest.getProducts();
            List<Product> products = new ArrayList<>();

            for (ProductRequestRoutine productRequestRoutine : productRequestRoutines) {
                Product product = productRepository.findById(productRequestRoutine.getId());
                if (product == null) {
                    throw new RuntimeException("Product not found");
                }else {
                    product.setRoutineSteps(routineSteps);  // Thiết lập mối quan hệ với RoutineSteps
                    products.add(product);
                }
            }
            routineStep.setProducts(products);  // Thiết lập sản phẩm cho RoutineStep
            routineSteps.add(routineStep);
        }
        // Cập nhật danh sách steps vào routine
        routine.setRoutineSteps(routineSteps);
        routineRepository.save(routine);  // Lưu lại routine với các steps
        return routineRepository.save(routine);
    }

    public Object updateRoutine(Long id, Routine routine) {
        return routineRepository.save(routine);
    }

    public void deleteRoutine(Long id) {
    }

    public Object createRoutineStep(Long routineId, RoutineStep routineStep) {
    return null;
    }

    public Object updateRoutineStep(Long id, RoutineStep routineStep) {
    return null;
    }

    public void deleteRoutineStep(Long id) {
    }
}
